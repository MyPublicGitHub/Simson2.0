package com.simson.www.net.bean.main;

public class ProgramBean {
    /**
     * Id : 1
     * Name :  鎷ユ姳
     * Url : http://47.99.72.252/636814147509909763?id=1
     * Describe : [寮瑰敱] 浼佸垝閮ㄥ搧鐗岀粍
     * Votes : 0
     * Selection : false
     */
    private String Id;
    private String Name;
    private String Url;
    private String Describe;

    public String getBallot() {
        return ballot;
    }

    public void setBallot(String ballot) {
        this.ballot = ballot;
    }

    private String ballot;
    private int Votes;
    private boolean Selection;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String Describe) {
        this.Describe = Describe;
    }

    public int getVotes() {
        return Votes;
    }

    public void setVotes(int Votes) {
        this.Votes = Votes;
    }

    public boolean isSelection() {
        return Selection;
    }

    public void setSelection(boolean Selection) {
        this.Selection = Selection;
    }
}
