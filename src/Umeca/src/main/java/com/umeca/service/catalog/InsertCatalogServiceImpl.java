package com.umeca.service.catalog;

import com.umeca.infrastructure.ReaderFile;
import com.umeca.infrastructure.extensions.IntegerExt;
import com.umeca.infrastructure.extensions.LongExt;
import com.umeca.model.catalog.*;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.VerificationMethod;
import com.umeca.model.entities.shared.CourseType;
import com.umeca.model.entities.shared.SchoolDocumentType;
import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.humanResources.CourseTypeRepository;
import com.umeca.repository.humanResources.SchoolDocumentTypeRepository;
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
public class InsertCatalogServiceImpl implements InsertCatalogService {

    @Autowired
    RoleRepository repositoryRole;
    @Autowired
    UserRepository repositoryUser;
    @Autowired
    QuestionTypeRepository repositoryQuTy;
    @Autowired
    QuestionaryRepository repositoryQuy;
    @Autowired
    QuestionarySectionRepository repositoryQuSe;
    @Autowired
    QuestionRepository repositoryQun;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    StatusMeetingRepository statusMeetingRepository;
    @Autowired
    StatusCaseRepository repositoryStCase;
    @Autowired
    SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    ActivityGoalRepository activityGoalRepository;
    @Autowired
    ActivityGroupRepository activityGroupRepository;
    @Autowired
    AidSourceRepository aidSourceRepository;
    @Autowired
    StatusVerificationRepository repositoryStVer;
    @Autowired
    ArrangementRepository repositoryArr;
    @Autowired
    MaritalStatusRepository maritalStatusRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    DrugTypeRepository drugTypeRepository;
    @Autowired
    PeriodicityRepository periodicityRepository;
    @Autowired
    DayWeekRepository dayWeekRepository;
    @Autowired
    AcademicLevelRepository academicLevelRepository;
    @Autowired
    DegreeRepository degreeRepository;
    @Autowired
    RegisterTypeRepository registerTypeRepository;
    @Autowired
    FramingRiskRepository framingRiskRepository;
    @Autowired
    HomeTypeRepository homeTypeRepository;
    @Autowired
    FramingThreatRepository framingThreatRepository;
    @Autowired
    FieldVerificationRepository fieldVerificationRepository;
    @Autowired
    StatusFieldVerificationRepository statusFieldVerificationRepository;
    @Autowired
    VerificationMethodRepository verificationMethodRepository;
    @Autowired
    RequestTypeRepository requestTypeRepository;
    @Autowired
    SystemSettingRepository systemSettingRepository;
    @Autowired
    CatFileTypeRepository fileTypeRepository;
    @Autowired
    ResponseTypeRepository responseTypeRepository;
    @Autowired
    FulfillmentReportTypeRepository fulfillmentReportTypeRepository;
    @Autowired
    ElectionNotApplyRepository electionNotApplyRepository;
    @Autowired
    GroupCrimeRepository groupCrimeRepository;
    @Autowired
    CrimeCatalogRepository crimeCatalogRepository;
    @Autowired
    TypeNameFileRepository typeNameFileRepository;
    @Autowired
    PriorityRepository priorityRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    CloseCauseRepository closeCauseRepository;
    @Autowired
    ImmigrationDocumentRepository immigrationDocumentRepository;
    @Autowired
    HearingTypeRepository hearingTypeRepository;
    @Autowired
    CourseTypeRepository courseTypeRepository;
    @Autowired
    SchoolDocumentTypeRepository schoolDocumentTypeRepository;


    //private String PATH = "/home/dcortesr/IdeaProjects/UmecaApp/db/";

    //C:\Users\Rata\Desktop\branchSandra\UmecaApp\db
    private String PATH = "C:\\Users\\rolnd_000\\Desktop\\branchSandra\\UmecaApp\\db\\";

    //http://localhost:8080/Umeca/catalogs/insertCatalgoAll.html
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
        List<String[]> lstDta = ReaderFile.readFile(PATH + "supervision_activity.txt", "\\|", 5);
        for (String[] data : lstDta) {
            SupervisionActivity model = new SupervisionActivity();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(Boolean.parseBoolean(data[3]));
            model.setSpecification(data[4].equals("1"));
            supervisionActivityRepository.save(model);
        }
        supervisionActivityRepository.flush();
    }

    @Override
    public void insertActivityGoal() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "activity_group.txt", "\\|", 3);
        for (String[] data : lstDta) {
            ActivityGroup model = new ActivityGroup();
            model.setId(Long.parseLong(data[0]));
            model.setCode(data[1]);
            model.setDescription(data[2]);
            activityGroupRepository.save(model);
        }

        lstDta = ReaderFile.readFile(PATH + "activity_goals.txt", "\\|", 6);
        for (String[] data : lstDta) {
            ActivityGoal model = new ActivityGoal();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(Boolean.parseBoolean(data[3]));
            model.setSpecification(data[4].equals("1"));
            model.setActivityGroup(activityGroupRepository.findOne(Long.parseLong(data[5])));
            activityGoalRepository.save(model);
        }
        activityGoalRepository.flush();
    }

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

    @Override
    public void arrangement() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "arrangement.txt", "\\|", 8);
        for (String[] data : lstDta) {
            Arrangement model = new Arrangement();
            model.setId(Long.parseLong(data[0]));
            model.setDescription(data[1]);
            model.setType(Integer.parseInt(data[2]));
            model.setIndex(Integer.parseInt(data[3]));
            model.setIsObsolete(data[4].equals("1"));
            model.setIsNational(data[5].equals("1"));
            model.setIsDefault(data[6].equals("1"));
            model.setIsExclusive(data[7].equals("1"));
            repositoryArr.save(model);
        }
        repositoryArr.flush();
    }

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

    @Override
    public void relationship() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "relationship.txt", "\\|", 4);
        for (String[] data : lstDta) {
            Relationship model = new Relationship();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setObsolete(data[2].equals("1"));
            model.setSpecification(data[3].equals("1"));
            relationshipRepository.save(model);
        }
        relationshipRepository.flush();
    }

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

    @Override
    public void fieldVerification() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "field_verification.txt", "\\|", 9);
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

    @Override
    public void statusFieldVerification() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "status_field_verification.txt", "\\|", 3);
        for (String[] data : lstDta) {
            StatusFieldVerification model = new StatusFieldVerification();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            statusFieldVerificationRepository.save(model);
        }
        statusFieldVerificationRepository.flush();
    }

    @Override
    public void verificationMethod() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "verification_method.txt", "\\|", 3);
        for (String[] data : lstDta) {
            VerificationMethod model = new VerificationMethod();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setObsolete(data[2].equals("1"));
            verificationMethodRepository.save(model);
        }
        verificationMethodRepository.flush();
    }

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

    @Override
    public void responseType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "response_type.txt", "\\|", 3);
        for (String[] data : lstDta) {
            ResponseType model = new ResponseType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            responseTypeRepository.save(model);
        }
        responseTypeRepository.flush();
    }

    @Override
    public void fulfillmentReport() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "fulfillment_report.txt", "\\|", 4);
        for (String[] data : lstDta) {
            FulfillmentReportType model = new FulfillmentReportType();
            model.setId(Long.parseLong(data[0]));
            model.setCode(data[1]);
            model.setName(data[2]);
            model.setObsolete(data[3].equals("1"));
            fulfillmentReportTypeRepository.save(model);
        }
        fulfillmentReportTypeRepository.flush();
    }

    @Override
    public void electionNotApply() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "election_not_apply.txt", "\\|", 2);
        for (String[] data : lstDta) {
            ElectionNotApply model = new ElectionNotApply();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            electionNotApplyRepository.save(model);
        }
        electionNotApplyRepository.flush();
    }

    @Override
    public void crime() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "group_crime.txt", "\\|", 4);
        for (String[] data : lstDta) {
            GroupCrime model = new GroupCrime();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setIsObsolete(data[3].equals("1"));
            groupCrimeRepository.save(model);
        }
        List<String[]> lstDtaCrime = ReaderFile.readFile(PATH + "crime.txt", "\\|", 5);
        for (String[] data : lstDtaCrime) {
            CrimeCatalog model = new CrimeCatalog();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setDescription(data[2]);
            model.setObsolete(data[3].equals("1"));
            model.setGroupCrime(groupCrimeRepository.findOne(Long.parseLong(data[4])));
            crimeCatalogRepository.save(model);
        }
    }

    @Override
    public void typeFileName() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "type_file_name.txt", "\\|", 6);
        for (String[] data : lstDta) {
            TypeNameFile model = new TypeNameFile();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setCode(data[2]);
            model.setOnly(data[3].equals("1"));
            model.setObsolete(data[4].equals("1"));
            String[] roles = data[5].split(",");
            model.setRoles(new ArrayList<Role>());
            for (String s : roles) {
                model.getRoles().add(repositoryRole.findByCode(s));
            }
            typeNameFileRepository.save(model);
        }
        typeNameFileRepository.flush();
    }

    @Override
    public void priority() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "priority.txt", "\\|", 5);
        for (String[] data : lstDta) {
            CatPriority model = new CatPriority();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setColor(data[2]);
            model.setDescription(data[3]);
            model.setIsObsolete(data[4].equals("1"));
            priorityRepository.save(model);
        }
        priorityRepository.flush();
    }

    @Override
    public void district() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "district.txt", "\\|", 3);
        for (String[] data : lstDta) {
            District model = new District();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setIsObsolete(data[2].equals("1"));
            districtRepository.save(model);
        }
        districtRepository.flush();
    }

    @Override
    public void closeCause() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "close_cause.txt", "\\|", 3);
        for (String[] data : lstDta) {
            CloseCause model = new CloseCause();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setIsObsolete(data[2].equals("1"));
            closeCauseRepository.save(model);
        }
        closeCauseRepository.flush();
    }

    @Override
    public void immigrationDocument() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "immigrationDocument.txt", "\\|", 4);
        for (String[] data : lstDta) {
            ImmigrationDocument model = new ImmigrationDocument();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setSpecification(data[2].equals("1"));
            model.setObsolete(data[3].equals("1"));
            immigrationDocumentRepository.save(model);
        }
        immigrationDocumentRepository.flush();
    }

    @Override
    public void hearingType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "hearing_type.txt", "\\|", 5);

        for (String[] data : lstDta) {
            HearingType model = new HearingType();
            model.setId(Long.parseLong(data[0]));
            model.setDescription(data[1]);
            model.setIsObsolete(data[2].equals("1"));
            model.setLock(data[3].equals("1"));
            model.setSpecification(data[4].equals("1"));
            hearingTypeRepository.save(model);
        }
        hearingTypeRepository.flush();
    }

    @Override
    public void courseType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "course_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            CourseType model = new CourseType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setIsObsolete(data[2].equals("1"));
            model.setSpecification(data[3].equals("1"));
            courseTypeRepository.save(model);
        }
        courseTypeRepository.flush();
    }

    @Override
    public void schoolDocType() {
        List<String[]> lstDta = ReaderFile.readFile(PATH + "school_document_type.txt", "\\|", 4);
        for (String[] data : lstDta) {
            SchoolDocumentType model = new SchoolDocumentType();
            model.setId(Long.parseLong(data[0]));
            model.setName(data[1]);
            model.setIsObsolete(data[2].equals("1"));
            model.setSpecification(data[3].equals("1"));
            schoolDocumentTypeRepository.save(model);
        }
        schoolDocumentTypeRepository.flush();
    }

}