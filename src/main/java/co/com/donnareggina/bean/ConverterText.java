package co.com.donnareggina.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import co.com.donnareggina.model.Product;

@FacesConverter("converterText")
public class ConverterText implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return (String) value;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		return (value != null) ? ((String) value).replaceAll("'", "//") : null;

	}


	

}
