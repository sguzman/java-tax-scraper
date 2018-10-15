package org.sguzman.github.tax;

public class CourtCase {
  public String caseCause;
  public String style;
  public String fileDate;
  public String court;
  public String caseRegion;
  public String typeOfAction;

  public static int hash = 0;

  public int thisHash;

  public CourtCase(String _caseCause, String _style, String _fileDate, String _court, String _caseRegion, String _typeOfAction) {
    this.caseCause = _caseCause;
    this.style = _style;
    this.fileDate = _fileDate;
    this.court = _court;
    this.caseRegion = _caseRegion;
    this.typeOfAction = _typeOfAction;

    this.thisHash = ++hash;
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

  @Override
  public int hashCode() {
    return this.thisHash;
  }

  @Override
  public boolean equals(Object that) {
    if((that == null) || (getClass() != that.getClass())){
      return false;
    }

    CourtCase thatt = (CourtCase) that;

    return this.caseCause.equals(thatt.caseCause) &&
          this.style.equals(thatt.style) &&
          this.fileDate.equals(thatt.fileDate) &&
          this.court.equals(thatt.court) &&
          this.caseRegion.equals(thatt.caseRegion) &&
          this.typeOfAction.equals(thatt.typeOfAction);
  }
}
