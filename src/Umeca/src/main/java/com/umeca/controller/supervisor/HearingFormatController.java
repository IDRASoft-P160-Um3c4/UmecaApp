package com.umeca.controller.supervisor;

import org.springframework.stereotype.Controller;

@Controller
public class HearingFormatController {

  /*  @Qualifier("qArrangementRepository")
    @Autowired
    ArrangementRepository arrangementRepository;

    @Qualifier("registerTypeRepository")
    @Autowired
    RegisterTypeRepository registerTypeRepository;

    @Qualifier("electionRepository")
    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    CaseService caseService;

    @Autowired
    HearingFormatService hearingFormatService;

    @Autowired
    CatalogService catalogService;

    @Autowired
    StatusCaseRepository statusCaseRepository;


    @RequestMapping(value = "/supervisor/hearingFormat", method = RequestMethod.GET)
    public String hearingFormat() {

        return "/supervisor/hearingFormat";
    }

    @RequestMapping(value = "/supervisor/searchCase", method = RequestMethod.POST)
    public
    @ResponseBody
    HearingFormatView searchCase(@RequestBody String idFolder) {

        HearingFormatView hearingFormatView;

        Case caseDet = caseService.findByIdFolder(idFolder);
        hearingFormatView = hearingFormatService.fillForView(caseDet, idFolder);

        return hearingFormatView;
    }

    @RequestMapping(value = "/supervisor/searchArrangements", method = RequestMethod.POST)
    public
    @ResponseBody
    String searchArrangement(@RequestBody String folderId) {

        Gson conv = new Gson();
        List<ArrangementView> lstArrangementView = hearingFormatService.getArrangmentLst(folderId);
        String jsonLst = conv.toJson(lstArrangementView);

        return jsonLst;
    }


    @RequestMapping(value = "/supervisor/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute HearingFormatView result) {

        ResponseMessage response = new ResponseMessage();
        try {

            Case caseDet = caseService.findByIdFolder(result.getIdFolderCode());

            HearingFormat hearingFormat;
            hearingFormat = hearingFormatService.fillHearingFormat(result);

            if (caseDet != null && caseDet.getHearingFormat() == null) {

                caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
                caseDet.setHearingFormat(hearingFormat);
                hearingFormat.setCaseDetention(caseDet);
                response = hearingFormatService.save(hearingFormat);
            } else if (caseDet == null) {

                Imputed imp = new Imputed();
                imp.setName(result.getImputedName());
                imp.setLastNameP(result.getImputedFLastName());
                imp.setLastNameM(result.getImputedSLastName());
                imp.setCelPhone(result.getImputedTel());
                imp.setDateBirth(result.getImputedBirthDate());

                caseDet = caseService.generateNewCase(imp, result.getHearingType());
                caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
                caseDet.setIdFolder(result.getIdFolderCode());
                caseDet.setIdMP(result.getIdJudicialFolderCode());

                ImputedHome currDom = new ImputedHome();

                currDom.setRegisterType(registerTypeRepository.findOne(Constants.REGYSTER_TYPE_CURRENT));
                currDom.setBelong(electionRepository.findOne(Constants.ELECTION_NO));
                currDom.setLocation(catalogService.findLocationById(result.getIdLocation()));

                currDom.setStreet(result.getStreet());
                currDom.setNoOut(result.getOutNum());
                currDom.setNoIn(result.getInnNum());
                currDom.setAddressString(currDom.toString());

                currDom.setMeeting(caseDet.getMeeting());
                List<ImputedHome> lstDom = new ArrayList<>();
                lstDom.add(currDom);
                caseDet.getMeeting().setImputedHomes(lstDom);

                hearingFormat.setCaseDetention(caseDet);
                caseDet.setHearingFormat(hearingFormat);

                caseDet = caseService.save(caseDet);
            }

            response.setHasError(false);
            response.setMessage(caseDet.getIdFolder());

        } catch (Exception e) {

            System.out.println("Error al guardar el formato de audiencia!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }
                  */

}
