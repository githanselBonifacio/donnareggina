package co.com.donnareggina.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.util.EscapeUtils;

@ManagedBean(name ="uploadData")
@SessionScoped
public class UploadData implements Serializable{

	
	private static final long serialVersionUID = 8363222592622464483L;
	private UploadedFile file;
    private UploadedFiles files;
    private String dropZoneText = "Drop zone p:inputTextarea demo.";
    
    
    private List<byte[]> images;
    
    @PostConstruct
	public void init() {
    	this.images = new   ArrayList<byte[]>();
    }
    
	public List<byte[]> getImages() {
		return images;
	}
	public void setImages(List<byte[]> images) {
		this.images = images;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public UploadedFiles getFiles() {
		return files;
	}
	public void setFiles(UploadedFiles files) {
		this.files = files;
	}
	public String getDropZoneText() {
		return dropZoneText;
	}
	public void setDropZoneText(String dropZoneText) {
		this.dropZoneText = dropZoneText;
	}
    
	//Cargar imagenes
    public void upload() {
        if (file != null) {
        	this.images.add(file.getContent());
            FacesMessage message = new FacesMessage("Correcto!!", file.getFileName() + " fue cargada");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void uploadMultiple() {
        if (files != null) {
            for (UploadedFile f : files.getFiles()) {
            	this.images.add(f.getContent());
                FacesMessage message = new FacesMessage("Correcto!!", f.getFileName() + " fue cargada");
                FacesContext.getCurrentInstance().addMessage(null, message);
  
            }
        }else {
        	FacesMessage message = new FacesMessage("Error al cargar imagenes");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
    		System.out.println("Inicio: "+this.images.size());
			this.images.clear();
    		byte [] img = event.getFile().getContent(); 
			this.images.add(img);
            FacesMessage message = new FacesMessage("Correcto!!", event.getFile().getFileName() + " fue cargada");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("Final: "+this.images.size());
    }
    
    

    public void handleFileUploadTextarea(FileUploadEvent event) {
        String jsVal = "PF('textarea').jq.val";
        String fileName = EscapeUtils.forJavaScript(event.getFile().getFileName());
        PrimeFaces.current().executeScript(jsVal + "(" + jsVal + "() + '\\n\\n" + fileName + " uploaded.')");
    }

    public void handleFilesUpload(FilesUploadEvent event) {
    	
    	for (UploadedFile f : event.getFiles().getFiles()) {
			this.images.add(f.getContent());
            FacesMessage message = new FacesMessage("Correcto!!", f.getFileName() + " fue cargado M"+this.images.isEmpty());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
  
    }

	
}
