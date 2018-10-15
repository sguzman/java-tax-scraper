package org.sguzman.github.tax;

public class CourtCase {
  public String caseCause;
  public String style;
  public String fileDate;
  public String court;
  public String caseRegion;
  public String typeOfAction;

  public CourtCase(String _caseCause, String _style, String _fileDate, String _court, String _caseRegion, String _typeOfAction) {
    this.caseCause = _caseCause;
    this.style = _style;
    this.fileDate = _fileDate;
    this.court = _court;
    this.caseRegion = _caseRegion;
    this.typeOfAction = _typeOfAction;
  }

  @Override
  public String toString() {
    return "CourtCase{" +
        "caseCause='" + caseCause + '\'' +
        ", style='" + style + '\'' +
        ", fileDate='" + fileDate + '\'' +
        ", court='" + court + '\'' +
        ", caseRegion='" + caseRegion + '\'' +
        ", typeOfAction='" + typeOfAction + '\'' +
        '}';
  }
}
