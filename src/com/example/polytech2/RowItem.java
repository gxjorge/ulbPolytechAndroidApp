package com.example.polytech2;

public class RowItem {
    
    private String title;
    private String desc;
    private boolean selected = false;
 
    public RowItem( String title, String desc, boolean selected) {
        
        this.title = title;
        this.desc = desc;
    }
   
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
    public boolean isSelected() {
    	return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}