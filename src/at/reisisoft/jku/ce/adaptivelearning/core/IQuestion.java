package at.reisisoft.jku.ce.adaptivelearning.core;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import at.reisisoft.jku.ce.adaptivelearning.xml.XmlQuestionData;

public interface IQuestion<DataStorage extends AnswerStorage> {
	/**
	 *
	 * @return The right answer to the question
	 */
	public DataStorage getSolution();

	/**
	 *
	 * @return The answer made by the user. A question, which is read to be
	 *         displayed to the user must return NULL
	 */
	public DataStorage getUserAnswer();

	/**
	 *
	 * @return Checks if the user answer is the, or alternatively, one of the
	 *         right answers
	 */
	public boolean checkUserAnswer();

	/**
	 *
	 * @return The difficulty. -INF the easiest, +INF the hardest
	 */
	public float getDifficulty();

	/**
	 *
	 * @return A representation of the question, which can be parsed to XML
	 */
	public XmlQuestionData<DataStorage> toXMLRepresentation();

	/**
	 *
	 * @return XML as String
	 * @throws JAXBException
	 *             Exception creating XML
	 */
	public default String toXML() throws JAXBException {
		XmlQuestionData<DataStorage> xmlRepresentation = toXMLRepresentation();
		Class<? extends AnswerStorage> dataStorageClass = xmlRepresentation
				.getDataStorageClass();
		JAXBContext context = JAXBContext.newInstance(
				xmlRepresentation.getClass(), dataStorageClass);
		Marshaller marshaller = context.createMarshaller();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		marshaller.marshal(xmlRepresentation, byteArrayOutputStream);
		try {
			return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Should never happen, as "UTF-8" is hard coded
			return "FAILED " + e.getMessage();
		}
	}
}
