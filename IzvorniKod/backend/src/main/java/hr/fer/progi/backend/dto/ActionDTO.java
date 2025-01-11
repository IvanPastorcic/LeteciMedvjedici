package hr.fer.progi.backend.dto;

public class ActionDTO {


    private String actionName;
    private String settlementName;
    private String description;

    public ActionDTO(String actionName, String settlementName, String description) {
        this.actionName = actionName;
        this.settlementName = settlementName;
        this.description = description;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getSettlementName() {
        return settlementName;
    }

    public void setSettlementName(String settlementName) {
        this.settlementName = settlementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
