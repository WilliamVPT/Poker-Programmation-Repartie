/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;
import java.util.Arrays;

import main.java.org.qcm.*
;
public class App {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        // Register new users
        String uid1 = userManager.registerUser("William", "william@example.com");
        String uid2 = userManager.registerUser("Alice", "alice@example.com");

        // Retrieve a user
        User user = userManager.getUser(uid1);
        if (user != null) {
            System.out.println("User: " + user.getUsername() + ", Email: " + user.getEmail());
        } else {
            System.out.println("User not found.");
        }

        userManager.addQCMToUser(uid2, "QCM001", "QCM1", 85);
        userManager.addQCMToUser(uid2, "QCM002", "QCM2", 90);

        QCM qcm = new QCM(
                "1234",
                "Quizz sur le riche Rémi Rahir",
                Arrays.asList(
                        new Question("q1", "Combien Rémi a t'il investit sur LMT", Arrays.asList("500", "1000", "5", "5123698"), 1),
                        new Question("q2", "A quel pourcentage la calivitie de Rémi est-elle avancé", Arrays.asList("20%", "10%", "80%", "Plus un cheveux sur le cailloux"), 4)
                        // Ajoutez d'autres questions
                )
        );

        // Convertir en JSON
        String jsonQCM = QCMSerializer.serializeQCM(qcm);

        // Publier dans RabbitMQ
        RabbitMQPublisher.publishQCM(jsonQCM);
    }
}
