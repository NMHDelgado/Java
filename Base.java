import java.sql.*;

public class Base {
    public static void main(String[] args) {
        // Pour ceux travaillant une base de donnees mysql, ils auront
        // String url = "jdbc:mysql://localhost:3306/test";
        // String userName = "root";
        // String password = "";
        String url = "jdbc:postgresql://localhost:5432/test";
        String userName = "postgres";
        String password = "passwod"; // Remplacer ce password par le mot de passe de votre serveur de BD

        // Creation d'un utilisateur en BD
        try{
            //Connexion a la BD
            Connection connected = DriverManager.getConnection(url, userName, password);
            
            // Requete SQL
            String query = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            //CReation d'une requete preparee
            PreparedStatement state = connected.prepareStatement(query);

            //Passage des valeurs de champ au statement
            state.setString(1,"Emperio");
            state.setString(2,"delta@gmail.com");
            state.setString(3, "motdepasse");

            //Execution de la requete
            state.executeUpdate();

            System.out.println("Utilisateur cree");

            //Fermeture des objets
            state.close();
            connected.close();


        }catch(SQLException e){
            e.printStackTrace();
        }


        //Lecture d'un utilisateur
        try{
            //Connexion a la BD
            Connection connected = DriverManager.getConnection(url, userName, password);

            // Requete SQL
            String query = "SELECT * FROM users";

            //Execution de la requete
            Statement state = connected.createStatement();
            ResultSet result = state.executeQuery(query);

            //Lecture des objets de la table
            while(result.next()){
                System.out.println(result.getString("name") + " - " + result.getString("email") + " - " + result.getString("password"));
            }

            connected.close();
            state.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Mise a jour d'un utilisateur en BD
        try{
            //Connexion a la BD
            Connection connected = DriverManager.getConnection(url, userName, password);
            
            // Requete SQL
            String query = "UPDATE users SET name = ?, email = ?, password= ? WHERE name = ?";

            //Preparation de la requete
            PreparedStatement state = connected.prepareStatement(query);

            //Passage des parametres
            state.setString(1, "John");
            state.setString(2, "john@gmail.com");
            state.setString(3, "password");
            state.setString(4, "Jon");

            //Execution de la requete
            int lines = state.executeUpdate();

            state.close();
            connected.close();
            if(lines > 0){
                System.out.println("Utilisateur mis a jour");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        //Suppression d'un utilisateur
        try{
            //Connexion a la BD
            Connection connected = DriverManager.getConnection(url, userName, password);

            //Ecriture de la requete de suppression
            String query = "DELETE FROM users WHERE email = ?";

            //Preparation de la requete
            PreparedStatement state = connected.prepareStatement(query);


            //Passage des parametres
            state.setString(1, "john@gmail.com");
            
            //Execution de la requete
            int lines = state.executeUpdate();

            if(lines > 0){
                System.out.println("Utilisateur supprime");
            }

            state.close();
            connected.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
       
    }
}