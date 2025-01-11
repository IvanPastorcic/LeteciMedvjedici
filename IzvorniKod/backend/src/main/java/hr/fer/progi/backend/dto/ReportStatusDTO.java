package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.model.Enum.ReportStatus;

public class ReportStatusDTO {

    ReportStatus status;

    public ReportStatusDTO(ReportStatus status) {
        this.status = status;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
