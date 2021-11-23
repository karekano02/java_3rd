    @ApiOperation("PDF Download Sample1")
    @RequestMapping("/apiSamplePDF")
    public ResponseEntity<InputStreamResource> apiSamplePDF() throws Exception
    {
        List<SampleModel> sampleModelList = sampleService.getSampleCode();

        ByteArrayInputStream inputStream = sampleService.getPdf(sampleModelList);
        String filename = String.format("download_test_%s.pdf", LocalDateTime.now().toString());

        HttpHeaders headers = new HttpHeaders();

//        headers.add("Content-Disposition", "inline; filename=" + filename); // view
        headers.add("Content-Disposition", "attachment; filename=" + filename); // 다운로드

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
    


// service

   @Override
    public ByteArrayInputStream getPdf(List<SampleModel> sampleModelList){
        Document document = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            document = new Document();


            // 한글 처리를 위한 글꼴 설정 추가
            String fontPath = "/static/css/fonts/malgun.ttf";
            BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            Font titleFont = new Font(bf, 15, Font.BOLD);
            Paragraph title = new Paragraph("문 서 제 목", titleFont);

            Font subTitleFont = new Font(bf, 14, Font.NORMAL);
            DateFormat titledf = new SimpleDateFormat("yyyy-MM-dd");
            Paragraph subTitle = new Paragraph("문서 소제목", subTitleFont);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 2, 2, 3 });

            Font contentsFont = new Font(bf, 11, Font.NORMAL);

            Font headFont = new Font(bf, 11, Font.BOLD);
            table.addCell(setHeadCell("필드1", headFont));
            table.addCell(setHeadCell("필드2", headFont));
            table.addCell(setHeadCell("필드3", headFont));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
            int index = 0;
            for (SampleModel sampleModel : sampleModelList) {
                table.addCell(setChildCell(sampleModelList.get(index).getGrCode() == null ? "-" : sampleModelList.get(index).getGrCode()));
                table.addCell(setChildCellH(sampleModelList.get(index).getGrCodeNm() == null ? "-" : sampleModelList.get(index).getGrCodeNm(),contentsFont));
                table.addCell(setChildCell(sampleModelList.get(index).getUseYn() == null ? "-" : sampleModelList.get(index).getUseYn()));
                index++;
            }

            PdfWriter.getInstance(document, outputStream);

            document.open();
            document.add(title);
            document.add(Chunk.NEWLINE);
            document.add(subTitle);
            document.add(Chunk.NEWLINE);
            document.add(table);

        } catch (DocumentException | IOException ex) {
            log.error("PDF 파일 생성 실패");
        } finally {

            if(document != null) {

                document.close();

            }

        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private PdfPCell setHeadCell(String content, Font font) {
        PdfPCell hcell = new PdfPCell(new Phrase(content, font));
        hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(new BaseColor(226, 226, 226));
        hcell.setFixedHeight(25f);
        return hcell;
    }

    private PdfPCell setChildCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(20f);
        return cell;
    }

    //한글 contents
    private PdfPCell setChildCellH(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(20f);
        return cell;
    }
