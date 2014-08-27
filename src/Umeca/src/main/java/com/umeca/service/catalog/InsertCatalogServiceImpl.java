package com.umeca.service.catalog;

import com.umeca.infrastructure.ReaderFile;
import com.umeca.infrastructure.extensions.IntegerExt;
import com.umeca.infrastructure.extensions.LongExt;
import com.umeca.model.catalog.*;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.VerificationMethod;
import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.repository.shared.CatFileTypeRepository;
import com.umeca.repository.shared.QuestionaryRepository;
import com.umeca.repository.shared.SystemSettingRepository;
import com.umeca.repository.supervisor.*;
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

    private String PATH = "C:\\Projects\\IDRASoft\\UmecaApp\\db\\";

    @Autowired
    RoleRepository repositoryRole;

    @Override
    public void role() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "role.txt", "\\|", 3);

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
        List<String[]> lstDta = ReaderFile.readFile(PATH + "user.txt", "\\|", 7);

        for (String[] data : lstDta) {
            User model = new User();
            model.setId(Long.parseLong(data[0]));
            model.setUsername(data[1]);
            model.setPassword(data[2]);
            model.setEmail(data[3]);
            model.setFullname(data[4]);
            model.setEnabled(Boolean.parseBoolean(data[5]));
            final Role role = repositoryRole.findByCode(data[6]);
            model.setRoles(new ArrayList<Role>() {{
                add(role);
            }});
            repositoryUser.save(model);
        }

        repositoryUser.flush();
    }

    @Autowired
    QuestionTypeRepository repositoryQuTy;

    @Override
    public void questionType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "question_type.txt", "\\|", 4);
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
        List<String[]> lstDta = ReaderFile.readFile(PATH + "questionary.txt", "\\|", 5);
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
        List<String[]> lstDta = ReaderFile.readFile(PATH + "questionary_section.txt", "\\|", 8);
        for (String[] data : lstDta) {
            QuestionarySection model = new QuestionarySection();
            model.setId(Long.parseLong(data[0]));
            model.setCode(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(Boolean.parseBoolean(data[3]));
            model.setName(data[4]);

            Long id = LongExt.TryParse(data[5]);
            if (id != null) {
                QuestionarySection qs = repositoryQuSe.findOne(id);
                model.setParent(qs);
            }

            id = LongExt.TryParse(data[6]);
            if (id != null) {
                Questionary q = repositoryQuy.findOne(id);
                model.setQuestionary(q);
            }

            model.setExtras(data[7]);

            repositoryQuSe.save(model);
        }
        repositoryQuSe.flush();
    }


    @Autowired
    QuestionRepository repositoryQun;

    @Override
    public void question() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "question.txt", "\\|", 7);
        for (String[] data : lstDta) {
            Question model = new Question();
            model.setId(Long.parseLong(data[0]));
            model.setIsObsolete(Boolean.parseBoolean(data[1]));
            model.setQuestion(data[2]);
            model.setValue(Integer.parseInt(data[3]));

            Long id = LongExt.TryParse(data[4]);
            if (id != null) {
                QuestionType qt = repositoryQuTy.findOne(id);
                model.setQuestionType(qt);
            }

            id = LongExt.TryParse(data[5]);
            if (id != null) {
                QuestionarySection qs = repositoryQuSe.findOne(id);
                model.setSection(qs);
            }

            model.setIndex(IntegerExt.TryParse(data[6]));

            repositoryQun.save(model);
        }
        repositoryQun.flush();
    }

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void activity() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "activity.txt", "\\|", 4);

        for (String[] data : lstDta) {
            Activity model = new Activity();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setSpecification(data[2].equals("1"));
            model.setObsolete(data[3].equals("1"));
            activityRepository.save(model);
        }

        activityRepository.flush();

    }

    @Autowired
    StatusMeetingRepository statusMeetingRepository;

    @Override
    public void statusMeeting() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "status_meeting.txt", "\\|", 3);
        for (String[] data : lstDta) {
            StatusMeeting model = new StatusMeeting();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            statusMeetingRepository.save(model);
        }
        statusMeetingRepository.flush();
    }

    @Autowired
    StatusCaseRepository repositoryStCase;

    @Override
    public void statusCase() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "status_case.txt", "\\|", 3);
        for (String[] data : lstDta) {
            StatusCase model = new StatusCase();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            repositoryStCase.save(model);
        }
        repositoryStCase.flush();
    }

    @Override
    public void supervisionActivity() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "supervision_activity.txt", "\\|", 4);
        for (String[] data : lstDta) {
            SupervisionActivity model = new SupervisionActivity();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setObsolete(Boolean.parseBoolean(data[3]));
            supervisionActivityRepository.save(model);
        }
        supervisionActivityRepository.flush();
    }


    @Autowired
    SupervisionActivityRepository supervisionActivityRepository;


    @Autowired
    ActivityGoalRepository activityGoalRepository;

    @Override
    public void insertActivityGoal() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "activity_goals.txt", "\\|", 4);
        for (String[] data : lstDta) {
            ActivityGoal model = new ActivityGoal();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setObsolete(Boolean.parseBoolean(data[3]));
            activityGoalRepository.save(model);
        }
        activityGoalRepository.flush();
    }

    @Autowired
    AidSourceRepository aidSourceRepository;
    @Override
    public void insertAidSource() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "aid_source.txt", "\\|", 4);
        for (String[] data : lstDta) {
            AidSource model = new AidSource();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setObsolete(Boolean.parseBoolean(data[3]));
            aidSourceRepository.save(model);
        }
        aidSourceRepository.flush();
    }

    @Autowired
    StatusVerificationRepository repositoryStVer;

    @Override
    public void statusVerification() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "status_verification.txt", "\\|", 3);
        for (String[] data : lstDta) {
            StatusVerification model = new StatusVerification();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            repositoryStVer.save(model);
        }
        repositoryStVer.flush();
    }

    @Autowired
    ArrangementRepository repositoryArr;

    @Override
    public void arrangement() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "arrangement.txt", "\\|", 6);
        for (String[] data : lstDta) {
            Arrangement model = new Arrangement();
            model.setId(Long.parseLong(data[0]));
            model.setDescription(data[1]);
            model.setType(Integer.parseInt(data[2]));
            model.setIndex(Integer.parseInt(data[3]));
            model.setIsObsolete(data[4].equals("1"));
            model.setIsNational(data[5].equals("1"));
            repositoryArr.save(model);
        }
        repositoryArr.flush();
    }

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Override
    public void maritalStatus() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "marital_status.txt", "\\|", 2);

        for (String[] data : lstDta) {
            MaritalStatus model = new MaritalStatus();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            ;
            maritalStatusRepository.save(model);
        }

        maritalStatusRepository.flush();
    }

    @Autowired
    ElectionRepository electionRepository;

    @Override
    public void election() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "election.txt", "\\|", 2);
        for (String[] data : lstDta) {
            Election model = new Election();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            ;
            electionRepository.save(model);
        }
        electionRepository.flush();
    }


    @Autowired
    RelationshipRepository relationshipRepository;

    @Override
    public void relationship() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "relationship.txt", "\\|", 3);
        for (String[] data : lstDta) {
            Relationship model = new Relationship();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            ;
            model.setObsolete(data[2].equals("1"));
            relationshipRepository.save(model);
        }
        relationshipRepository.flush();
    }

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Override
    public void documentType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "document_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            DocumentType model = new DocumentType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            ;
            model.setSpecification(data[2].equals("1"));
            model.setObsolete(data[3].equals("1"));
            documentTypeRepository.save(model);
        }
        documentTypeRepository.flush();
    }

    @Autowired
    DrugTypeRepository drugTypeRepository;

    @Override
    public void drugType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "drug_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            DrugType model = new DrugType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setObsolete(data[2].equals("1"));
            model.setSpecification(data[3].equals("1"));
            drugTypeRepository.save(model);
        }
        drugTypeRepository.flush();

    }

    @Autowired
    PeriodicityRepository periodicityRepository;

    @Override
    public void periodicity() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "periodicity.txt", "\\|", 4);
        for (String[] data : lstDta) {
            Periodicity model = new Periodicity();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            ;
            model.setSpecification(data[2].equals("1"));
            model.setObsolete(data[3].equals("1"));
            periodicityRepository.save(model);
        }
        periodicityRepository.flush();
    }

    @Autowired
    DayWeekRepository dayWeekRepository;

    @Override
    public void dayWeek() {

        List<String[]> lstDta = ReaderFile.readFile(PATH + "day_week.txt", "\\|", 2);
        for (String[] data : lstDta) {
            DayWeek model = new DayWeek();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            ;
            dayWeekRepository.save(model);
        }
        dayWeekRepository.flush();
    }

    @Autowired
    AcademicLevelRepository academicLevelRepository;

    @Autowired
    DegreeRepository degreeRepository;

    @Override
    public void academicDegree() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "academic_level.txt", "\\|", 3);
        for (String[] data : lstDta) {
            AcademicLevel model = new AcademicLevel();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setObsolete(data[2].equals("1"));
            academicLevelRepository.save(model);
        }
        List<String[]> lstDtaGrade = ReaderFile.readFile(PATH + "degree.txt", "\\|", 4);
        for (String[] data : lstDtaGrade) {
            Degree model = new Degree();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setAcademicLevel(academicLevelRepository.findOne(Long.parseLong(data[2])));
            model.setObsolete(data[3].equals("1"));
            degreeRepository.save(model);
        }
        academicLevelRepository.flush();
    }

    @Autowired
    RegisterTypeRepository registerTypeRepository;

    @Override
    public void registerType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "register_type.txt", "\\|", 2);
        for (String[] data : lstDta) {
            RegisterType model = new RegisterType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            registerTypeRepository.save(model);
        }
        registerTypeRepository.flush();
    }

    /* CATALOGO DE TIPO DE AUDIENCIA (FORMATO DE AUDIENCIA) NO APLICA PARA ESTA VERSIÓN
        @Autowired
        HearingFormatTypeRepository hearingFormatTypeRepository;
        @Override
        public void hearingFormatType() {
            List<String[]> lstDta = ReaderFile.readFile(PATH + "hearing_format_type.txt","\\|", 4);

            for (String[] data : lstDta) {
                HearingFormatType model = new HearingFormatType();
                model.setId(Long.parseLong(data[0]));
                model.setName(data[1]);
                model.setDescription(data[2]);
                model.setIsObsolete(data[3].equals("1"));
                hearingFormatTypeRepository.save(model);
            }

            hearingFormatTypeRepository.flush();
        }
    */
    @Autowired
    FramingRiskRepository framingRiskRepository;

    @Override
    public void framingRisk() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "framing_risk.txt", "\\|", 4);
        for (String[] data : lstDta) {
            FramingRisk model = new FramingRisk();

            model.setId(Long.parseLong(data[0]));
            model.setDescription(data[1]);
            model.setIndex(Long.parseLong(data[2]));
            model.setIsObsolete(data[3].equals("1"));

            framingRiskRepository.save(model);
        }
        framingRiskRepository.flush();
    }

    @Autowired
    HomeTypeRepository homeTypeRepository;
    @Override
    public void homeType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "home_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            HomeType model = new HomeType();

            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setSpecification(data[2].equals("1"));
            model.setObsolete(data[3].equals("1"));

            homeTypeRepository.save(model);
        }
        homeTypeRepository.flush();
    }

    @Autowired
    FramingThreatRepository framingThreatRepository;

    @Override
    public void framingThreat() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "framing_threat.txt", "\\|", 4);
        for (String[] data : lstDta) {
            FramingThreat model = new FramingThreat();

            model.setId(Long.parseLong(data[0]));
            model.setDescription(data[1]);
            model.setIndex(Long.parseLong(data[2]));
            model.setIsObsolete(data[3].equals("1"));

            framingThreatRepository.save(model);
        }
        framingThreatRepository.flush();
    }
    @Autowired
    FieldVerificationRepository fieldVerificationRepository;
    @Override
    public void fieldVerification() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "field_verification.txt","\\|", 9);
        for (String[] data : lstDta) {
            FieldVerification model = new FieldVerification();
            model.setId(Long.parseLong(data[0]));
            model.setCode(data[1]);
            model.setSection(data[2]);
            model.setSectionCode(Integer.parseInt(data[3]));
            model.setFieldName(data[4]);
            model.setIndexField(Integer.parseInt(data[5]));
            model.setObsolete(data[6].equals("1"));
            model.setIdSubsection(Integer.parseInt(data[7]));
            model.setType(data[8]);
            fieldVerificationRepository.save(model);
        }
        fieldVerificationRepository.flush();
    }

    @Autowired
    StatusFieldVerificationRepository statusFieldVerificationRepository;
    @Override
    public void statusFieldVerification() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "status_field_verification.txt","\\|", 3);
        for (String[] data : lstDta) {
            StatusFieldVerification model = new StatusFieldVerification();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            statusFieldVerificationRepository.save(model);
        }
        statusFieldVerificationRepository.flush();
    }

    @Autowired
    VerificationMethodRepository verificationMethodRepository;
    @Override
    public void verificationMethod() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "verification_method.txt","\\|", 3);
        for (String[] data : lstDta) {
            VerificationMethod model = new VerificationMethod();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setObsolete(data[2].equals("1"));
            verificationMethodRepository.save(model);
        }
        verificationMethodRepository.flush();
    }

    @Autowired
    RequestTypeRepository requestTypeRepository;
    @Override
    public void requestType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "request_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            RequestType model = new RequestType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(data[3].equals("1"));
            requestTypeRepository.save(model);
        }
        requestTypeRepository.flush();
    }


    @Autowired
    SystemSettingRepository systemSettingRepository;
    @Override
    public void systemSettings() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "system_settings.txt", "\\|", 5);
        for (String[] data : lstDta) {
            SystemSetting model = new SystemSetting();
            model.setId(Long.parseLong(data[0]));
            model.setGroup(data[1]);
            model.setKey(data[2]);
            model.setValue(data[3]);
            model.setDescription(data[4]);
            systemSettingRepository.save(model);
        }
        systemSettingRepository.flush();
    }


    @Autowired
    CatFileTypeRepository fileTypeRepository;
    @Override
    public void fileType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "file_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            CatFileType model = new CatFileType();
            model.setId(Long.parseLong(data[0]));
            model.setFileType(data[1]);
            model.setDescription(data[2]);
            model.setObsolete(data[3].equals("1"));
            fileTypeRepository.save(model);
        }
        fileTypeRepository.flush();
    }

}