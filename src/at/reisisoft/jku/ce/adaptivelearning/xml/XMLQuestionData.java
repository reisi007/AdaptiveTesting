package at.reisisoft.jku.ce.adaptivelearning.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;

@XmlRootElement(name = "questionDataStorage")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLQuestionData<DataStorage extends AnswerStorage> implements
		Serializable {

	private static final long serialVersionUID = 5422318817914536294L;

	private DataStorage dataStorage;
	private String question;
	private float difficulty;

	public XMLQuestionData() {
		this(null, "", 0);
	}

	public XMLQuestionData(DataStorage dataStorage, String question,
			float difficulty) {
		this.dataStorage = dataStorage;
		this.difficulty = difficulty;
		this.question = question;

	}

	public DataStorage getDataStorage() {
		return dataStorage;
	}

	public void setDataStorage(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}

	// public static void main(String[] args) throws JAXBException {
	// ByteArrayOutputStream byteArrayOutputStream = new
	// ByteArrayOutputStream();
	// JAXBContext context = JAXBContext.newInstance(XMLQuestionData.class);
	// Marshaller marshaller = context.createMarshaller();
	// marshaller.marshal(new XMLQuestionData(), byteArrayOutputStream);
	// System.out.println(new String(byteArrayOutputStream.toByteArray()));
	// }

}
