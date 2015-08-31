package com.umeca.controller.director;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.project.*;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.shared.Constants;
import com.umeca.repository.director.ProjectActivityRepository;
import com.umeca.repository.director.ProjectRepository;
import com.umeca.repository.shared.UploadFileGenericRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/director/project/index", method = RequestMethod.GET)
    public String index() {
        return "/director/project/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/director/project/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("creationDate"));
                    add(r.get("status"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                return null;
            }
        }, Project.class, ProjectView.class);

        return result;
    }

    @Autowired
    ProjectRepository repositoryProject;

    @RequestMapping(value = "/director/project/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id) {
        ModelAndView modelView = new ModelAndView("/director/project/upsert");

        if (id != null) {
            Project model = repositoryProject.findOne(id);
            modelView.addObject("model", model);
        }

        return modelView;
    }

    @RequestMapping(value = "/director/project/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute Project modelNew) {

        ResponseMessage response = new ResponseMessage();

        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            Project model;
            if (modelNew.getId() > 0) {
                model = repositoryProject.findOne(modelNew.getId());
                model.setName(modelNew.getName());
                model.setDescription(modelNew.getDescription());
                model.setUpdaterUser(user);
            } else {
                model = modelNew;
                model.setIsObsolete(false);
                model.setStatus(Constants.PROJECT_STATUS_ACTIVE);
                model.setCreationDate(Calendar.getInstance());
                model.setCreatorUser(user);
            }

            repositoryProject.save(model);
            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsert", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }

        return response;
    }


    @RequestMapping(value = "/director/project/doObsolete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsolete(@RequestParam(required = true) Long id) {

        ResponseMessage response = new ResponseMessage();
        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            Project model = repositoryProject.findOne(id);

            repositoryProjectActivity.setObsoleteToActivitiesByProjectId(id);

            model.setStatus(Constants.PROJECT_STATUS_INACTIVE);
            model.setIsObsolete(true);
            repositoryProject.save(model);
            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doObsolete", userService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
            return response;
        }
    }

    //Activities
    @RequestMapping(value = {"/director/project/listActivities"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listActivities(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long id) {
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("projectId", id.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("creationDate"));
                    add(r.get("name"));
                    add(r.get("description"));
                    add(r.join("uploadFileGeneric", JoinType.LEFT).get("id"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                if (field.equals("projectId"))
                    return r.join("project").get("id");
                return null;
            }
        }, ProjectActivity.class, ProjectActivityView.class);
        return result;
    }

    @Autowired
    ProjectActivityRepository repositoryProjectActivity;

    @RequestMapping(value = "/director/project/upsertActivity", method = RequestMethod.POST)
    public ModelAndView upsertActivity(@RequestParam(required = true) Long projectId, Long id) {
        ModelAndView modelView = new ModelAndView("/director/project/upsertActivity");
        modelView.addObject("projectId", projectId);

        if(id == null)
            id = 0l;

        modelView.addObject("id", id);
        return modelView;
    }

    @Autowired
    UploadFileGenericRepository uploadFileGenericRepository;

    @RequestMapping(value = "/director/project/doUpsertActivity", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertActivity(@ModelAttribute final ProjectActivityModel modelNew) {

        ResponseMessage response = new ResponseMessage();

        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            ProjectActivity model = new ProjectActivity();
            model.setName(modelNew.getName());
            model.setDescription(modelNew.getDescription());
            model.setProject(new Project(){{setId(modelNew.getProjectId());}});
            model.setIsObsolete(false);
            model.setCreationDate(Calendar.getInstance());
            model.setCreatorUser(user);

            Long fileId = modelNew.getFileUploadGenericId();

            if(fileId != null){
                UploadFileGeneric uploadFileGeneric = uploadFileGenericRepository.findOne(fileId);
                model.setUploadFileGeneric(uploadFileGeneric);
                uploadFileGeneric.setObsolete(false);
                uploadFileGenericRepository.save(uploadFileGeneric);
            }

            repositoryProjectActivity.save(model);
            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertActivity", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }

        return response;
    }


    @RequestMapping(value = "/director/project/doObsoleteActivity", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsoleteActivity(@RequestParam(required = true) Long id) {

        ResponseMessage response = new ResponseMessage();
        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            ProjectActivity model = repositoryProjectActivity.findOne(id);
            model.setIsObsolete(true);

            UploadFileGeneric uploadFileGeneric = model.getUploadFileGeneric();
            if(uploadFileGeneric != null && uploadFileGeneric.getId() != null){
                uploadFileGeneric.setObsolete(true);
                uploadFileGenericRepository.save(uploadFileGeneric);
            }

            repositoryProjectActivity.save(model);
            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doObsoleteActivity", userService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
            return response;
        }
    }

    @RequestMapping(value = "/director/project/endProject", method = RequestMethod.POST)
    public ModelAndView endProject(@RequestParam(required = true) Long id) {
        ModelAndView modelView = new ModelAndView("/director/project/endProject");
        modelView.addObject("projectId", id);
        List<String> lstNames = repositoryProject.getProjectNameById(id);
        modelView.addObject("projectName", lstNames.get(0));
        return modelView;
    }

    @RequestMapping(value = "/director/project/doEndProject", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doEndProject(@ModelAttribute final ProjectModel modelNew) {

        ResponseMessage response = new ResponseMessage();

        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            Project model = repositoryProject.findOne(modelNew.getId());
            model.setComments(modelNew.getComments());
            model.setEndDate(Calendar.getInstance());
            model.setDeleteUser(user);
            model.setStatus(Constants.PROJECT_STATUS_FINISHED);

            repositoryProject.save(model);
            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertActivity", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }

        return response;
    }

}
