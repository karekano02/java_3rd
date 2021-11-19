
@Slf4j
@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    ExcelUtil excelUtil;

    @Operation(summary = "테스트 샘플", description = "text api 테스트 샘플")
    @RequestMapping("/fo/sample")
    public String apiSample(@Parameter(description = "텍스트 샘플", required = true, example = "") @RequestParam String name)
    {
        TestUtil testUtil = new TestUtil();
        testUtil.testPrint();

        log.error(name);
        sampleService.getSampleCode()
                    .stream().map(code -> code.getGrCode())
                    .forEach(log::info);
        return "Fo Test Sample";
    }

    @RequestMapping("/xss/useFilter")
    public String useFilter(@RequestBody Map<String,String> request) throws Exception {
        String inputMsg = request.get("request");
        String convertMsg = XssPreventer.escape(inputMsg);
        String reconvertMsg = XssPreventer.unescape(inputMsg);
        System.out.println("### Get Message(Use XSS Filter) ###");
        System.out.println("### 입력 => " + inputMsg);
        System.out.println("### 치환 => " + convertMsg);
        System.out.println("### 역치환 => " + reconvertMsg);

        return "useFilter";
    }


    @ApiOperation("로그인")
    @PostMapping("/sample/login_process")
    public ResponseEntity<CommonResponseModel> sampleLogin(@ApiParam("로그인 요청 정보") @RequestBody @Valid SampleLoginVO loginVo
                                                    , HttpServletResponse response){
        return sampleService.loginProcess(loginVo,response);
    }

    @ApiOperation("멤버등록")
    @RequestMapping("/sample/rgMember")
    public ResponseEntity<CommonResponseModel> rgMember(@RequestBody AccountInfo aVo){
        try{
            //log.info("aVo.toString():::::"+aVo.toString());
            return sampleService.rgMember(aVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @ApiOperation("CORS")
    @RequestMapping("/fo/cors")
    public String corsTest(){
        return "test complete!!!! ";
    }


    @ApiOperation("template")
    @RequestMapping("/fo/template")
    public String foTemplate()throws Exception{

        System.out.println("template 시작");
        RestTemplate restTemplate = new RestTemplate();

        //Url
        String url ="http://localhost:8082/mo/template";

        //MethodType
        HttpMethod HttpMethodType = HttpMethod.POST;
//           HttpMethod type = HttpMethod.GET;

        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","test key");

        //Body
        SampleModel requestModel = new SampleModel();
        requestModel.setGrCode("test1");
        requestModel.setGrCodeNm("test2");

        //Request
        HttpEntity request = new HttpEntity<>(requestModel,headers);

        //ResponseClass
        Class<Object> responseClass = Object.class;

        ResponseEntity<Object> responseEntity =
        restTemplate.exchange(
                url,
                HttpMethodType,
                request,
                responseClass
        );



        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received");
            System.out.println(responseEntity.getBody());

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<SampleModel>> typeRef = new TypeReference<List<SampleModel>>() {};
            List<SampleModel> sampleModels =
                    mapper.convertValue(responseEntity.getBody(),typeRef);

            System.out.println("sampleModelVo :::" +sampleModels.get(1));
            System.out.println("GrCodeNm :::" +sampleModels.get(1).getGrCode());

        } else {
            System.out.println("error occurred");
            System.out.println(responseEntity.getStatusCode());
        }

        System.out.println("template 끝");

        //throw new Exception();
        //throw new NullPointerException();
        //throw new CommonException(ErrorCode.CANNOT_FOLLOW_MYSELF.value,"Errmsg");
        return null;
    }


    @ApiOperation("template")
    @RequestMapping("/fo/template2")
    public void foTemplate2() throws Exception {
        RestTemplate restTemplate = new RestTemplate("http://localhost:8082/mo/template");

        SampleModel requestModel = new SampleModel();
        requestModel.setGrCode("test1");
        requestModel.setGrCodeNm("test2");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","test key");

       ResponseEntity responseEntity =  restTemplate.exchage(requestModel,null);

//       if (responseEntity.getStatusCodeValue()>HttpStatus.IM_USED.value()){
//           log.info("======================>",HttpStatus.IM_USED.value());
//       }
//       else {
           ObjectMapper mapper = new ObjectMapper();
           TypeReference<List<SampleModel>> typeRef = new TypeReference<List<SampleModel>>() { };
           List<SampleModel> sampleModels =
                   mapper.convertValue(responseEntity.getBody(), typeRef);

           System.out.println("sampleModelVo :::" + sampleModels.get(1));
           System.out.println("GrCodeNm :::" + sampleModels.get(1).getGrCode());
//       }

    }

    @ApiOperation("Excel Download Sample")
    @GetMapping("/fo/sampleExcel")
    public void apiSampleExcel(HttpServletResponse response) throws IOException
    {
        TestUtil testUtil = new TestUtil();
        testUtil.testPrint();
        List<SampleModel> sampleModelList = sampleService.getSampleCode();
        sampleModelList.stream().map(code -> code.getGrCode())
                        .forEach(log::info);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("sampleSheet");

        Row row = null;
        Cell cell = null;
        int rowIdx = 0;
        int cellIdx = 0;

        //Header 생성
        row = sheet.createRow(rowIdx++);
        cell = row.createCell(cellIdx++);
        cell.setCellValue("그룹코드");
        cell = row.createCell(cellIdx++);
        cell.setCellValue("그룹코드명");
        cell = row.createCell(cellIdx++);
        cell.setCellValue("사용여부");

        for(int i=0;i<sampleModelList.size();i++){
            cellIdx = 0;
            row = sheet.createRow(rowIdx++);
            cell = row.createCell(cellIdx++);
            cell.setCellValue(sampleModelList.get(i).getGrCode());
            cell = row.createCell(cellIdx++);
            cell.setCellValue(sampleModelList.get(i).getGrCodeNm());
            cell = row.createCell(cellIdx++);
            cell.setCellValue(sampleModelList.get(i).getUseYn());
        }
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=sample.xlsx");
        wb.write(response.getOutputStream());
        wb.close();
   }

    @ApiOperation("Excel Download Sample")
    @GetMapping("/fo/sampleExcelService")
    public void apiSampleExcelService(HttpServletResponse response) throws IOException{
        List<String> columneNames = new ArrayList<>();
        List<String> headerNames = new ArrayList<>();
        columneNames.add("grCode");
        columneNames.add("grCodeNm");
        columneNames.add("useYn");

        headerNames.add("그룹코드");
        headerNames.add("그룹코드명");
        headerNames.add("사용여부");

        Map<String, String> param = new HashMap<>();
        param.put("useYn","Y");
        Function<Map<String,String>,List<SampleModel>> function = sampleService::getSampleCode;
        excelUtil.makeExcelFileToResponse("sampleService.xlsx",headerNames,columneNames, function, response, param);
    }

    @ApiOperation("Excel Download Sample")
    @GetMapping("/fo/sampleExcelService2")
    public void apiSampleExcelService2(HttpServletResponse response) throws IOException{
        List<String> columneNames = new ArrayList<>();
        List<String> headerNames = new ArrayList<>();
        columneNames.add("grCode");
        columneNames.add("grCodeNm");
        columneNames.add("useYn");

        headerNames.add("그룹코드");
        headerNames.add("그룹코드명");
        headerNames.add("사용여부");

        Map<String, String> param = new HashMap<>();
        param.put("useYn","Y");
        sampleService.getSampleCode(response,param);
//        sampleService.getSampleCode()
//        Function<Map<String,String>,List<SampleModel>> function = sampleService::getSampleCode;
//        excelUtil.makeExcelFileToResponse("sampleService.xlsx",headerNames,columneNames, function, response, param);
    }



    @ApiOperation("Excel Download Sample2")
    @GetMapping("/fo/sampleExcel2")
    public void apiSampleExcel2(HttpServletResponse response) throws IOException
    {
        TestUtil testUtil = new TestUtil();
        testUtil.testPrint();
        List<SampleModel> sampleModelList = sampleService.getSampleCode();
        sampleModelList.stream().map(code -> code.getGrCode())
                .forEach(log::info);

        SXSSFWorkbook wb = new SXSSFWorkbook();
        wb.setCompressTempFiles(true);
        SXSSFSheet sheet = wb.createSheet("sampleSheet");
        sheet.setRandomAccessWindowSize(1000);  //메모리 size

        Row row = null;
        Cell cell = null;
        int rowIdx = 0;
        int cellIdx = 0;

        //Header 생성
        row = sheet.createRow(rowIdx++);
        cell = row.createCell(cellIdx++);
        cell.setCellValue("그룹코드");
        cell = row.createCell(cellIdx++);
        cell.setCellValue("그룹코드명");
        cell = row.createCell(cellIdx++);
        cell.setCellValue("사용여부");

        for(int i=0;i<sampleModelList.size();i++){
            cellIdx = 0;
            row = sheet.createRow(rowIdx++);
            cell = row.createCell(cellIdx++);
            cell.setCellValue(sampleModelList.get(i).getGrCode());
            cell = row.createCell(cellIdx++);
            cell.setCellValue(sampleModelList.get(i).getGrCodeNm());
            cell = row.createCell(cellIdx++);
            cell.setCellValue(sampleModelList.get(i).getUseYn());
        }
        String filename = "sampleExcel2.xlsx";
        String orgFileName = "sampleExcel2.xlsx"; // 서버저장파일명
        String fileDownLoadPath = "/resource/"; // 파일생성

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        wb.write(response.getOutputStream());

//        FileOutputStream fos = new FileOutputStream(fileDownLoadPath + orgFileName);
//        wb.write(fos);
        wb.close();
        wb.dispose();
//        Map<String,String> resultMap = new HashMap<>();
//        resultMap.put("filePath", fileDownLoadPath);
//        resultMap.put("realFilNm", orgFileName);
//        resultMap.put("viewFileNm", filename);

    }

    @Operation(summary = "테스트 오라클", description = "오라클 DB 테스트 샘플")
    @GetMapping("/fo/testOracle")
    public String testOracle()
    {
        sampleService.testOracle();

        return "test Oracle";
    }


}
