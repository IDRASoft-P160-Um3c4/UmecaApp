package com.umeca.service.catalog;

import com.umeca.infrastructure.ReaderFile;
import com.umeca.infrastructure.extensions.LongExt;
import com.umeca.model.catalog.Question;
import com.umeca.model.catalog.QuestionType;
import com.umeca.model.catalog.Questionary;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.QuestionRepository;
import com.umeca.repository.catalog.QuestionTypeRepository;
import com.umeca.repository.catalog.QuestionarySectionRepository;
import com.umeca.repository.shared.QuestionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/20/14
 * Time: 4:27 PM
 */
@Service("insertCatalogService")
public class InsertCatalogServiceImpl implements InsertCatalogService{

    private String PATH = "C:\\Projects\\IDRASoft\\UmecaApp\\db\\";//"C:\\Projects\\IDRASoft\\UmecaApp\\db\\";


    @Autowired
    RoleRepository repositoryRole;

    @Override
    public void role() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "role.txt","\\|", 3);

        for (String[] data : lstDta) {
            Role model = new Role();
            model.setId(Long.parseLong(data[0]));
            model.setRole(data[1]);
            model.setDescription(data[2]);
            repositoryRole.save(model);
        }

        repositoryRole.flush();
    }


    @Autowired
    UserRepository repositoryUser;

    @Override
    public void user() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "user.txt","\\|", 7);

        for (String[] data : lstDta) {
            User model = new User();
            model.setId(Long.parseLong(data[0]));
            model.setUsername(data[1]);
            model.setPassword(data[2]);
            model.setEmail(data[3]);
            model.setFullname(data[4]);
            model.setEnabled(Boolean.parseBoolean(data[5]));
            final Role role = repositoryRole.findByCode(data[6]);
            model.setRoles(new ArrayList<Role>(){{add(role);}});
            repositoryUser.save(model);
        }

        repositoryUser.flush();
    }

    @Autowired
    QuestionTypeRepository repositoryQuTy;

    @Override
    public void questionType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "question_type.txt","\\|", 4);
        for (String[] data : lstDta) {
            QuestionType model = new QuestionType();
            model.setId(Long.parseLong(data[0]));
            model.setDescription(data[1]);
            model.setIsObsolete(Boolean.parseBoolean(data[2]));
            model.setName(data[3]);
            repositoryQuTy.save(model);
        }
        repositoryQuTy.flush();
    }

    @Autowired
    QuestionaryRepository repositoryQuy;

    @Override
    public void questionary() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "questionary.txt","\\|", 5);
        for (String[] data : lstDta) {
            Questionary model = new Questionary();
            model.setId(Long.parseLong(data[0]));
            model.setCode(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(Boolean.parseBoolean(data[3]));
            model.setName(data[4]);
            repositoryQuy.save(model);
        }
        repositoryQuy.flush();
    }


    @Autowired
    QuestionarySectionRepository repositoryQuSe;

    @Override
    public void questionarySection() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "questionary_section.txt","\\|", 7);
        for (String[] data : lstDta) {
            QuestionarySection model = new QuestionarySection();
            model.setId(Long.parseLong(data[0]));
            model.setCode(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(Boolean.parseBoolean(data[3]));
            model.setName(data[4]);

            Long id = LongExt.TryParse(data[5]);
            if(id != null){
                QuestionarySection qs = repositoryQuSe.findOne(id);
                model.setParent(qs);
            }

            id = LongExt.TryParse(data[6]);
            if(id != null){
                Questionary q = repositoryQuy.findOne(id);
                model.setQuestionary(q);
            }

            repositoryQuSe.save(model);
        }
        repositoryQuSe.flush();
    }


    @Autowired
    QuestionRepository repositoryQun;

    @Override
    public void question() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "question.txt","\\|", 6);
        for (String[] data : lstDta) {
            Question model = new Question();
            model.setId(Long.parseLong(data[0]));
            model.setIsObsolete(Boolean.parseBoolean(data[1]));
            model.setQuestion(data[2]);
            model.setValue(Integer.parseInt(data[3]));

            Long id = LongExt.TryParse(data[4]);
            if(id != null){
                QuestionType qt = repositoryQuTy.findOne(id);
                model.setQuestionType(qt);
            }

            id = LongExt.TryParse(data[5]);
            if(id != null){
                QuestionarySection qs = repositoryQuSe.findOne(id);
                model.setSection(qs);
            }

            repositoryQun.save(model);
        }
        repositoryQun.flush();
    }
}