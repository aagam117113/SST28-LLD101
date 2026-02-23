import java.nio.charset.StandardCharsets;

/**
 * LSP-compliant CSV exporter.
 * Contract: normalizes body content for CSV format by replacing newlines and
 * commas
 * with spaces. This is a documented, expected behavior of CSV export (not
 * silent corruption).
 * All exporters honor the base contract: never throw, handle all content
 * lengths.
 */
public class CsvExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String body = req.body == null ? "" : normalize(req.body);
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return ExportResult.success("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Documented normalization: newlines and commas are replaced for flat CSV rows.
     */
    private String normalize(String s) {
        return s.replace("\n", " ").replace(",", " ");
    }
}
