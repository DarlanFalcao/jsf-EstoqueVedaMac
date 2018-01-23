package br.com.vedamac.converters;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vedamac.model.Unidade;

@FacesConverter("UnidadeConverter")
public class UnidadeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("GETASOBJECT");
		if (arg2 != null) {  
            return this.getAttributesFrom(arg1).get(arg2);  
        }  
        return null;  
	}

	@Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		System.out.println("GETASSTRING");
		if (object != null  
                && !"".equals(object)) {  
  
            Unidade entity = (Unidade) object;  
  
            // adiciona item como atributo do componente  
            this.addAttribute(uic, entity);  
  
            String codigo = entity.getIdUnidade();  
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
  
        return (String) object;  
}
	 protected void addAttribute(UIComponent component, Unidade o) {  
	        String key = o.getIdUnidade().toString(); // codigo da empresa como chave neste caso  
	        this.getAttributesFrom(component).put(key, o);  
	    }  
	 
	 protected Map<String, Object> getAttributesFrom(UIComponent component) {  
	        return component.getAttributes();  
	    }  
	 
}
