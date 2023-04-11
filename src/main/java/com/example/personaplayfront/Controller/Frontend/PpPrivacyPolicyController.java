package com.example.personaplayfront.Controller.Frontend;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PpPrivacyPolicyController {

    @FXML
    public ScrollPane scrollPane;
    @FXML
    public Text policyTitle;

    @FXML
    public Text policyText;

    public void initialize() throws FileNotFoundException {
        policyTitle.setText("Privacy Policy");
        policyText.setText("PersonaPlay Streaming Service Terms and Conditions\n" +
                "\n" +
                "Welcome to PersonaPlay, a streaming service that provides access to movies, TV shows, and other video content (the \"Service\"). The Service is provided by PersonaPlay LLC (\"PersonaPlay\"), and these terms and conditions (the \"Terms\") govern your use of the Service. Please read the Terms carefully before using the Service.\n" +
                "\n" +
                "Acceptance of Terms\n" +
                "By using the Service, you agree to be bound by these Terms. If you do not agree to these Terms, do not use the Service.\n" +
                "\n" +
                "Changes to Terms\n" +
                "PersonaPlay may modify these Terms at any time, and such modifications shall be effective immediately upon posting of the modified Terms on the PersonaPlay website. You agree to review these Terms periodically to be aware of such modifications and your continued use of the Service shall be deemed to be your conclusive acceptance of the modified Terms.\n" +
                "\n" +
                "Use of Service\n" +
                "The Service is for personal, non-commercial use only. You may not use the Service for any illegal or unauthorized purpose. You agree to comply with all applicable laws and regulations in connection with your use of the Service.\n" +
                "\n" +
                "Subscription and Fees\n" +
                "In order to access the Service, you must have an active subscription. Subscription fees are billed in advance and are non-refundable. PersonaPlay reserves the right to change the subscription fees and to institute new charges at any time.\n" +
                "\n" +
                "Intellectual Property\n" +
                "The Service and its content, including without limitation the text, software, graphics, photos, sounds, music, videos, and interactive features, are owned by or licensed to PersonaPlay and are subject to copyright, trademark, and other intellectual property rights under United States and foreign laws and international conventions. You agree not to engage in the use, copying, or distribution of any content unless you have obtained the necessary permission from the owner.\n" +
                "\n" +
                "Disclaimer of Warranties\n" +
                "The Service is provided \"as is\" and without warranty of any kind, either express or implied, including without limitation any warranty for information, services, uninterrupted access, or products provided through or in connection with the Service. PersonaPlay does not warrant that the Service will be timely or error-free, that defects will be corrected, or that the Service or the server that makes it available is free of viruses or other harmful components.\n" +
                "\n" +
                "Limitation of Liability\n" +
                "In no event shall PersonaPlay be liable for any direct, indirect, incidental, special, or consequential damages arising out of or in any way connected with the use of the Service or with the delay or inability to use the Service, or for any information, products, and services obtained through the Service, or otherwise arising out of the use of the Service, whether based on contract, tort, strict liability, or otherwise.\n" +
                "\n" +
                "Governing Law\n" +
                "These Terms shall be governed by and construed in accordance with the laws of the State of California, without giving effect to any principles of conflicts of law.\n" +
                "\n" +
                "Termination\n" +
                "PersonaPlay may terminate your subscription and/or access to the Service at any time, without notice, for any reason or no reason.\n" +
                "\n" +
                "Indemnification\n" +
                "You agree to indemnify, defend, and hold harmless PersonaPlay, its affiliates, officers, directors, employees, agents, and licensors from and against any claims, actions, or demands, including without limitation reasonable legal fees, arising out of your use of the Service or your breach of these Terms.\n" +
                "Miscellaneous\n" +
                "These Terms constitute the entire agreement between you and PersonaPlay with respect to the Service, and supersede all prior or contemporaneous communications and proposals, whether oral or written, between you and PersonaPlay. If any provision of these Terms is held to be invalid or unenforceable, such provision shall be struck and the remaining provisions shall be enforced. PersonaPlay's failure to enforce any right or provision in these Terms shall not constitute a waiver of such right or provision unless acknowledged and agreed to by PersonaPlay in writing. These Terms do not create any agency, partnership, joint venture, or employment relationship, and you may not make any representations on PersonaPlay's behalf.\n" +
                "\n" +
                "Contact Us\n" +
                "If you have any questions or concerns about these Terms or the Service, please contact us at support@personaplay.com.\n" +
                "By using the Service, you acknowledge that you have read, understood, and agreed to these Terms.\n\n");

        //personaPlayText.setFont(font);
        FileInputStream reader = new FileInputStream("src/main/resources/com/example/personaplayfront/Font/p5hatty.ttf");
        FileInputStream reader2 = new FileInputStream("src/main/resources/com/example/personaplayfront/Font/p5hatty.ttf");

        policyTitle.setFont(javafx.scene.text.Font.loadFont(reader, 26));

        policyText.setFont(javafx.scene.text.Font.loadFont(reader2, 18));

        policyText.setWrappingWidth(1040);
    }
}
