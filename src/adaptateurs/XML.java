package adaptateurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import organisation.Cours;
import organisation.Etudiant;
import organisation.Inscription;

public class XML {

    public HashMap<String, Etudiant> getEtudiants()
    {
        HashMap<String, Etudiant> listeEtud = new HashMap<String, Etudiant>();
        Document doc = null;
        try
        {
            doc = parseXML("D:\\MIAGE\\ID\\TD1 - Médiateur\\Sources de données\\source3.xml");
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if(doc != null)
        {


            NodeList nList = doc.getElementsByTagName("Etudiant");
            for (int i = 0; i < nList.getLength(); i++)
            {
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                Element numEtElement =  (Element) eElement.getElementsByTagName("NumEt").item(0);
                Element NomEtElement =  (Element) eElement.getElementsByTagName("nom").item(0);
                Element TelephoneEtElement =  (Element) eElement.getElementsByTagName("telephone").item(0);
                Element ProvenanceElement =  (Element) eElement.getElementsByTagName("Provenance").item(0);
                Element FormationPrecedenteEtElement =  (Element) eElement.getElementsByTagName("FormationPrecedante").item(0);
                Element DateDeNaissanceEtElement =  (Element) eElement.getElementsByTagName("dateNaissance").item(0);
                Element AnneeDebutEtElement =  (Element) eElement.getElementsByTagName("AnneeDebut").item(0);
                Element Niveau_insertionEtElement =  (Element) eElement.getElementsByTagName("Niveau_insertion").item(0);
                Element PaysFormationPrecedenteEtElement =  (Element) eElement.getElementsByTagName("Pays_formation_precedante").item(0);
                //System.out.println("Manager ID : " + numEt.getAttribute("person"));
                listeEtud.put(numEtElement.getTextContent(), new Etudiant(Integer.parseInt(numEtElement.getTextContent()),NomEtElement.getTextContent(),"",ProvenanceElement.getTextContent(),
                        FormationPrecedenteEtElement.getTextContent(),PaysFormationPrecedenteEtElement.getTextContent(),Integer.parseInt(AnneeDebutEtElement.getTextContent()),0,Niveau_insertionEtElement.getTextContent()));
            }
            return listeEtud;
        }
        return listeEtud;
    }
    public HashMap<String, Cours> getCours()
    {
        HashMap<String, Cours> listeCours=new HashMap<String, Cours>();
        Document doc = null;
        try
        {
            doc = parseXML("D:\\MIAGE\\ID\\TD1 - Médiateur\\Sources de données\\source3.xml");
        }
        catch (ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        if(doc != null)
        {
            NodeList nList = doc.getElementsByTagName("Cours");

            for (int i = 1; i < nList.getLength(); i++)
            {
                Node nNode = nList.item(i);
                Element SousNoeudCours = (Element) nNode;
                Element eElement = (Element) SousNoeudCours;
                Element NbHeuresCoursElement =  (Element) eElement.getElementsByTagName("Nb_heures").item(0);
                Element NiveauCoursElement =  (Element) eElement.getElementsByTagName("Niveau").item(0);
                Element ID_cours =  (Element) eElement.getElementsByTagName("ID_cours").item(0);
                Element TypeCoursElement =  (Element) eElement.getElementsByTagName("Type").item(0);
                listeCours.put(ID_cours.getTextContent(), new Cours(Integer.parseInt(ID_cours.getTextContent()),"Aucun libelle", TypeCoursElement.getTextContent(),NiveauCoursElement.getTextContent(),Integer.parseInt(NbHeuresCoursElement.getTextContent())));

            }
            return listeCours;
        }
        return listeCours;
    }
    public HashMap<String, Inscription> getInscriptions()
    {
        HashMap<String, Inscription> listeInscriptions=new HashMap<>();
        Document doc = null;
        try
        {
            doc = parseXML("D:\\MIAGE\\ID\\TD1 - Médiateur\\Sources de données\\source3.xml");
        }
        catch (ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        if(doc != null)
        {
            NodeList nList = doc.getElementsByTagName("Inscription");

            for (int i = 0; i < nList.getLength(); i++)
            {
                Node nNode = nList.item(i);
                Element SousNoeudInscription = (Element) nNode;
                Element eElement = (Element) SousNoeudInscription;
                Element AnneeUniversitaireElement =  (Element) eElement.getElementsByTagName("Annee_universitaire").item(0);
                Element NumEtElement =  (Element) eElement.getElementsByTagName("NumEt").item(0);
                Element ID_CoursElement =  (Element) eElement.getElementsByTagName("ID_cours").item(0);
                Element Note_coursElement =  (Element) eElement.getElementsByTagName("Note_cours").item(0);
                listeInscriptions.put(ID_CoursElement.getTextContent() + NumEtElement.getTextContent() + AnneeUniversitaireElement.getTextContent(), new Inscription(Integer.parseInt(NumEtElement.getTextContent()), Integer.parseInt(ID_CoursElement.getTextContent()),
                        AnneeUniversitaireElement.getTextContent(),Integer.parseInt(Note_coursElement.getTextContent())));

            }
            return listeInscriptions;
        }
        return listeInscriptions;
    }

    private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().parse(new File(filePath));
        doc.getDocumentElement().normalize();
        return doc;
    }

}

