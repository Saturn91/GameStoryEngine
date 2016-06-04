package GameEventEngine.Dialogs.Dialog;

public class Option {
	private String name;
	private Text originText;
	private Text destinationText;
	
	public Option(String name, Text originText, Text destinationText) {
		this.name = name;
		this.originText = originText;
		this.destinationText = destinationText;
	}
	
	public String choose(){
		return destinationText.getName();
	}
	
	public boolean isOptionOf(String originText){
		return this.originText.getName().equals(originText);
	}
	
	public String getName(){
		return name;
	}
}
