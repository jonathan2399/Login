package mieibean;
import java.sql.*;

public class Login
{
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    private String ruolo;
    private String connect;
    private Connection connection;
    private Statement statement;
    
    public void set_nome(String n){
        if(n.length()>0)
            nome=n;
    }
    
    public void set_ruolo(String r){
        if(r.length()>0)
            ruolo=r;
    }
    
    public void set_cognome(String c){
        if(c.length()>0)
            cognome=c;
    }
    
    public void set_email(String e){
        if(e.length()>0)
            email=e;
    }

    public void set_username(String u){
        if(u.length()>0)
            username=u;
    }
    
    public void set_password(String p){
        if(p.length()>0)
            password=p;
    }
    
    public void carica_driver(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e) 
    {
        e.printStackTrace();
    } 
    }
    
    public void set_connect(String user, String porta, String database ){
        connect = "jdbc:mysql://" + user + ":" + porta + "/" + database;
    }
    
    public void crea_connessione (String username, String pass) throws SQLException{
        connection = DriverManager.getConnection(connect,username,pass);
        statement = connection.createStatement();
    }

    public void crea_tabella() throws SQLException{
        statement.execute("CREATE TABLE IF NOT EXISTS PERSONE ( Id_persona MEDIUMINT(5) NOT NULL AUTO_INCREMENT, Nome VARCHAR(20) NOT NULL, Cognome VARCHAR(20) NOT NULL, Email VARCHAR(30) NOT NULL, Username VARCHAR(30) NOT NULL, Password VARCHAR(20) NOT NULL, Ruolo VARCHAR(15) NOT NULL, PRIMARY KEY(Id_persona) );");
    }
    
    public int inserimento() throws SQLException{
        int r=0;
        r = statement.executeUpdate("INSERT INTO PERSONE (Nome, Cognome, Email, Username, Password, Ruolo)" + "VALUES('" + nome + "','" + cognome + "','" + email + "','" + username + "','" + password + "','" + ruolo + "')");
        return r;
    }
    
    public ResultSet controlla_user_email() throws SQLException{
        ResultSet result = statement.executeQuery("SELECT * FROM PERSONE WHERE Username = '" + username + "' OR Email = '" + email + "'");
        return result;
    }
    
    public ResultSet preleva(String username, String pass) throws SQLException{
        ResultSet result = statement.executeQuery("SELECT * FROM PERSONE WHERE Username = '" + username + "' AND Password = '" + pass + "'");
        return result;
    }
    
    public ResultSet preleva_studenti() throws SQLException{
        ResultSet result = statement.executeQuery("SELECT * FROM PERSONE WHERE Ruolo = 'studente' ");
        return result;
    }
    
    public ResultSet preleva_studenti_genitori() throws SQLException{
        ResultSet result = statement.executeQuery("SELECT * FROM PERSONE WHERE Ruolo = 'studente' OR Ruolo='genitore'");
        return result;
    }
    
    public ResultSet controlla_amm() throws SQLException{
        ResultSet result = statement.executeQuery("SELECT Username, Password, Nome, Email, Ruolo FROM PERSONE WHERE Ruolo = 'admin' ");
        return result;
    }
    
    public int inserisci_amm() throws SQLException{
        int r=0;
        r = statement.executeUpdate("INSERT INTO PERSONE (Nome, Cognome, Email, Username, Password, Ruolo)" + "VALUES('Amministratore','admin','admin@admin.it','admin','12345','admin')");
        return r;   
    }
    
    public int elimina_utente(String id) throws SQLException{
        int r=0;
        r = statement.executeUpdate("DELETE FROM PERSONE WHERE Id_persona = '" + id + "' ");
        return r;   
    }
    
    public String get_nome(){
        return nome;
    }
    
    public String get_cognome(){
        return cognome;
    }
    
    public String get_email(){
        return email;
    }
    
    public String get_ruolo(){
        return ruolo;
    }
    
    public String get_password(){
        return password;
    }
    
    public String get_connect(){
        return connect;
    } 

    public String get_username(){
        return username;
    }
}
