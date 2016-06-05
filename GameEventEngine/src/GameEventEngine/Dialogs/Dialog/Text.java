package GameEventEngine.Dialogs.Dialog;

public class Text {
	private String name;
	private String text;
	private boolean hasOptions;
	private boolean hasTransitionTracker = false;
	private int options = 0;
	private boolean alreadyRead = false;
	private Status status = Status.INACTIVE;
	private static final int lineDebugLenght = 30;

	public Text(String name, String text) {
		this.hasOptions = false;
		this.name = name;
		this.text = text;
	}
	
	public boolean alreadyRead(){
		return alreadyRead;
	}
	
	public void debug(){
		System.out.println("---" +name + ": ---");
		if(text.length() > lineDebugLenght){
			char[] textChar = text.toCharArray();
			int lines = text.length()/lineDebugLenght;
			for(int l = 0; l < lines+1; l++){
				for(int i = 0; i < lineDebugLenght; i++){
					if(l*lineDebugLenght+i < textChar.length){
						System.out.print(textChar[l*lineDebugLenght+i]);
					}
				}
				System.out.println();
			}
			
			
		}else{
			System.out.println(text);
		}
		
		System.out.println("--- this is only debug mode, alreadyRead has not changed! ---");
	}
	
	public void addOptions(){
		hasOptions = true;
		options++;
	}
	
	public String open(){
		alreadyRead = true;
		return text;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	
	public boolean isActive(){
		return status.equals(Status.ACTIVE);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean hasOption(){
		return hasOptions;
	}
	
	public void addTransitionTracker(){
		hasTransitionTracker = true;
	}
	
	public boolean hasTransitionTracker(){
		return hasTransitionTracker;
	}
	
	public int getOptionNum(){
		return options;
	}
		
	private enum Status {
		ACTIVE, INACTIVE;
	}
}
