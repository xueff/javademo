package mail.outlook;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.pff.*;
import java.util.*;

// my class is MyNetWork.
public class PstOst {

    // i declare a private variable (final for var) which is a string and named PST
    private final String PST;

    // I define a builder (constructeur) which takes the same name than the class
    public PstOst(String pstFilename){
        this.PST = pstFilename;
    }




    //programme MAIN
    public static void main(String[] args) {
        //I get the first arg with which my class will be called
//        String pstFilename = args[0];

        // I build an object from the MyNetWork class which takes this PST as an argument
        // and I call on this object my function GetInfo
        PstOst helloObj =
                new PstOst("C:\\Users\\admin\\AppData\\Local\\Microsoft\\Outlook\\xuefei@idss-cn.com (1).ost");
//                new MyNetWork("C:\\Users\\admin\\Documents\\Outlook 文件\\x.pst");
        helloObj.GetInfo();
        helloObj.GetInfo();
    }

    // public static void GetInfo(String filename) {
    public  void GetInfo() {
        try {
            PSTFile pstFile = new PSTFile(PST);
            // show pst name
            System.out.println(pstFile.getMessageStore().getDisplayName());

            // show rootfolder
            //processFolder(pstFile.getRootFolder());
            getEmailList(pstFile.getRootFolder());

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    // processFolder permet de naviguer dans les subfolders.
    int depth = -1;
    public void processFolder(PSTFolder folder)
            throws PSTException, java.io.IOException
    {
        depth++;
        // the root folder doesn't have a display name
        if (depth > 0) {
            printDepth();
            System.out.println(folder.getDisplayName());
        }

        // go through the folders...
        if (folder.hasSubfolders()) {
            Vector<PSTFolder> childFolders = folder.getSubFolders();
            for (PSTFolder childFolder : childFolders) {
                processFolder(childFolder);
            }
        }

        // and now the emails for this folder
        if (folder.getContentCount() > 0) {
            depth++;
            PSTMessage email = (PSTMessage)folder.getNextChild();
            while (email != null) {
                printDepth();
                System.out.println("Email: "+email.getSubject());
                email = (PSTMessage)folder.getNextChild();
            }
            depth--;
        }
        depth--;
    }

    // get list of emails.
    int depth2 = -1;
    public void getEmailList(PSTFolder folder)
            throws PSTException, java.io.IOException
    {
        depth2++;
        // go through the folders...
        if (folder.hasSubfolders()) {
            Vector<PSTFolder> childFolders = folder.getSubFolders();
            for (PSTFolder childFolder : childFolders) {
                getEmailList(childFolder);
            }
        }

        // MongoDB connection
//        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//        DB db = mongo.getDB("outlook");
//        DBCollection contact = db.getCollection("contact");


        // and now the emails for this folder
        if (folder.getContentCount() > 0) {
            depth2++;
            PSTMessage email = (PSTMessage)folder.getNextChild();


            while (email != null) {
                StringBuffer bf = new StringBuffer(/*"\n\n\n\n//////////////////////////////////////////////////////////////////////////////////////////\n"*/);
                // write title of email
                bf.append("Email: "+email.getSubject()+"");

                //for each email, get vector of recipients (including sender)
                bf.append(">>User: "+email.getSenderName());
                bf.append(">>Date: "+ DateUtil.format(email.getMessageDeliveryTime(),"yyyy-MM-dd HH:mm")+"\n");
                int nbContact = email.getNumberOfRecipients();
                for (int x = 0; x <= nbContact-1; x++) {
//                    bf.append("*************************************************************************************************************");
//                    bf.append(">>Contact: "+email.getRecipient(x).getDisplayName());
//                    bf.append("name"+email.getRecipient(x).getDisplayName()+"\n");
//                    bf.append("email"+email.getSubject()+"\n");
//                    contact.insert(document);
                }
                String body = email.getBody();
                if(body.length() <=0){
//                        bf.append("body"+ email.getBodyHTML()+"\n");
                    body = email.getBodyHTML();
                }else {
                    body = email.getBody();

//                        bf.append("body"+ email.getBody()+"\n");
                }
                System.out.println(bf.toString());

                FileUtil.appendUtf8String(bf.toString(),"E:\\mail.txt");
                try {

                    email = (PSTMessage)folder.getNextChild();
                }catch (Exception e){
                    FileUtil.appendUtf8String(e.getMessage()+"\n","E:\\mail.txt");

                    folder.moveChildCursorTo((folder.getContentCount())+1);
                    System.out.print(e.getMessage());
                }
            }
            depth2--;
        }
        depth2--;
    }


    // get contacts
    public void GetNetWork(PSTContact contact)
            throws PSTException, java.io.IOException
    {
        System.out.println("");
    }




    public void printDepth() {
        for (int x = 0; x < depth-1; x++) {
            System.out.print(" | ");
        }
        System.out.print(" |- ");
    }

}


