package co.com.donnareggina.model;

public enum InventoryStatus {

	 	INSTOCK("En Stock"),
	    OUTOFSTOCK("Fuera de Stock"),
	    LOWSTOCK("Bajo de Stock");

	    private String text;

	    InventoryStatus(String text) {
	        this.text = text;
	    }

	    public String getText() {
	        return text;
	    }
	    
	    public static InventoryStatus status(String status) {
	    	 switch (status){
	            case "En Stock":
	                return INSTOCK;

	            case "Fuera de Stock":
	                return OUTOFSTOCK;

	            case "Bajo de Stock":
	                return LOWSTOCK;


	            default: return null;

	        }
	    }
	
}
