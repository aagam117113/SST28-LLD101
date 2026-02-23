/**
 * Base contract for all exporters:
 * - export() must accept any non-null ExportRequest with any content length
 * - export() must never throw for valid (non-null) requests
 * - export() must not silently corrupt or drop data
 * - If an exporter has limitations, it should return an error ExportResult, not
 * throw
 * - Null request handling: throw IllegalArgumentException (precondition
 * enforced at base)
 */
public abstract class Exporter {

    public ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("ExportRequest must not be null");
        }
        return doExport(req);
    }

    protected abstract ExportResult doExport(ExportRequest req);
}
