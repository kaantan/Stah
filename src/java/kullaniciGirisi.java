import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

        
@WebServlet(urlPatterns = {"/kullaniciGirisi"})
public class kullaniciGirisi extends HttpServlet {
    
UserInfo yeniKullanici=new UserInfo();
private String dbuserName;
private String dbPassword;
private Connection con1;
private PreparedStatement ps1;
ResultSet rs1 = null;
    

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet kullaniciGirisi</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet kullaniciGirisi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    yeniKullanici.userName=request.getParameter("username");
    yeniKullanici.password=request.getParameter("password");
    PrintWriter out = response.getWriter();
    connectDb();
    if (yeniKullanici.userName.equals(dbuserName) && yeniKullanici.password.equals(dbPassword)) {  
    Result basariliGiris=new Result(1,"basari",yeniKullanici);
    Gson basari=new Gson();
    out.print(basari.toJson(basariliGiris));
    }   
    else{
    Result hataliGiris=new Result(0,"hata");

            }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
public void connectDb()
{
try{
Class.forName("org.apache.derby.jdbc.ClientDataSource");
con1 = DriverManager.getConnection("jdbc:derby://localhost:1527/staj","APP","app");
String sql = "select * from STAJ";
ps1= con1.prepareStatement(sql); 
rs1= ps1.executeQuery(); 
while (rs1.next()){
dbPassword=rs1.getString("SIFRE");
dbuserName=rs1.getString("KADI");
}}catch(Exception e){
e.printStackTrace();}

finally
{try
{con1.close();
ps1.close();}
catch(Exception e){
e.printStackTrace();
}}

}  
 /*public String login() {  
        //connectDb(yeniKullanici.userName);
        getUserList();
        if (yeniKullanici.userName.equals(dbuserName) && yeniKullanici.password.equals(dbPassword)) {  
            
        } else  
            return "hatali";  
    return null;
    }*/   
}  

