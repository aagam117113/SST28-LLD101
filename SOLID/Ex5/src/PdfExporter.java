import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        // LSP fix: instead of throwing, return an error result for content > 20 chars
        if (req.body != null && req.body.length() > 20) {
            return ExportResult.error("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return ExportResult.success("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
