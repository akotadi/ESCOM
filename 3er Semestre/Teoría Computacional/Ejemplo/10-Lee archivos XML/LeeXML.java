import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeeXML {

 public static void main(String argv[]) {

  try {
  File file = new File("ejemploXML.xml");

  DocumentBuilderFactory fabricaDocumentos = DocumentBuilderFactory.newInstance();

  DocumentBuilder documento= fabricaDocumentos.newDocumentBuilder();

  Document documentoAnalizado = documento.parse(file);

  documentoAnalizado.getDocumentElement().normalize();

  System.out.println("Root element " + documentoAnalizado.getDocumentElement().getNodeName());

  NodeList listaNodos = documentoAnalizado.getElementsByTagName("concept");
  System.out.println("Information of all Concepts. I read: "+listaNodos.getLength()+" nodes");

  for (int s = 0; s < listaNodos.getLength(); s++) {

    Node unNodo = listaNodos.item(s);

    if (unNodo.getNodeType() == Node.ELEMENT_NODE)
    {
    // CARGA UN ELEMENTO
    Element unElemento = (Element) unNodo;

    // TOMA EL CONTENIDO DE LA ETIQUETA term
    NodeList listaNodosDelElemento = unElemento.getElementsByTagName("term");
    Element primerElemento = (Element) listaNodosDelElemento.item(0);
    NodeList listaValores = primerElemento.getChildNodes();
    System.out.println("Term: "  + ((Node) listaValores.item(0)).getNodeValue());

    // TOMA EL CONTENIDO DE LA ETIQUETA word
    NodeList otraListaNodosDelElemento = unElemento.getElementsByTagName("word");
    Element otroPrimerElemento = (Element) otraListaNodosDelElemento.item(0);
    NodeList otraListaValores = otroPrimerElemento.getChildNodes();
    System.out.println("Words that describe concept: " + ((Node) otraListaValores.item(0)).getNodeValue());

    // TOMA EL CONTENIDO DE LA ETIQUETA relation
    NodeList listaRelation = unElemento.getElementsByTagName("relation");
    Element elementoRelation = (Element)listaRelation.item(0);

        // RELATION ES OPCIONAL, ENTONCES VERIFICA SI APARECE PREGUNTANDO POR DIFERENTE DE null
        if( elementoRelation != null )
        {
        NodeList hijosRelation = elementoRelation.getChildNodes();
        System.out.println("        RELATION: " + ((Node)hijosRelation.item(0)).getNodeValue());

            // TOMA EL CONTENIDO DE LA ETIQUETA id QUE ESTÁ DENTRO DE LA ETIQUETA relation
            NodeList listaID = elementoRelation.getElementsByTagName("id");
            Element elementoID = (Element) listaID.item(0);
            NodeList valoresID = elementoID.getChildNodes();
            System.out.println("        ID: " + ((Node) valoresID.item(0)).getNodeValue());
        }
    }
  }
  } catch (Exception e) {
    e.printStackTrace();
  }
 }
}


/*
  Algunos de estos valores llevan acentos, al crear el XML y intentar verlo en IE me da error.
<tag1>Máquina</tag1>
He pensado recorrer la cadena y sustituir á por a, es decir quitar acentos. ¿Alguien sabe otra forma de hacerlo?, ¿depende de la version del XML?, ¿no se pueden poner acentos en XML?. En la cabecera del XML tengo:
<?xml version="1.0" encoding="UTF-8"?>
Gracias por vuestra ayuda y un saludo


JGraph
JDump

 */














