import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        // LSP fix: consistent handling — null req already handled by base class
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return ExportResult.success("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null)
            return "";
        return s.replace("\"", "\\\"");
    }
}
