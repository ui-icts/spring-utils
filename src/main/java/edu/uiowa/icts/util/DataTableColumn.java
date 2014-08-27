package edu.uiowa.icts.util;

/**
 * @author rrlorent
 */
public class DataTableColumn {

	private String name = null;
	private String value = null;
	private String title = null;
	private boolean selected = true;

	public DataTableColumn( String name, String value, String title, boolean selected ) {
		this.name = name;
		this.title = title;
		this.value = value;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public boolean getSelected() {
		return selected;
	}

	public void setSelected( boolean selected ) {
		this.selected = selected;
	}
}