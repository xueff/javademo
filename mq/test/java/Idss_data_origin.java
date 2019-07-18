
import java.io.Serializable;

public class Idss_data_origin implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String url;
  private String drivername;
  private String hostname;
  private Long port;
  private String username;
  private String password;
  private String authtype;
  private String keytabfilepath;
  private String principal;
  private Long status;
  private java.sql.Timestamp create_time;
  private String create_user;
  private java.sql.Timestamp update_time;
  private String update_user;
  private Integer startIndex;
  private Integer pageSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public Long getPort() {
    return port;
  }

  public void setPort(Long port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAuthtype() {
    return authtype;
  }

  public void setAuthtype(String authtype) {
    this.authtype = authtype;
  }

  public String getKeytabfilepath() {
    return keytabfilepath;
  }

  public void setKeytabfilepath(String keytabfilepath) {
    this.keytabfilepath = keytabfilepath;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }

  public String getCreate_user() {
    return create_user;
  }

  public void setCreate_user(String create_user) {
    this.create_user = create_user;
  }

  public java.sql.Timestamp getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
  }

  public String getUpdate_user() {
    return update_user;
  }

  public void setUpdate_user(String update_user) {
    this.update_user = update_user;
  }

  public String getStatus_name() {
    if(status != 0) return "未使用";
    return "使用中";
  }

  public Integer getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(Integer startIndex) {
    this.startIndex = startIndex;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDrivername() {
    return drivername;
  }

  public void setDrivername(String drivername) {
    this.drivername = drivername;
  }
}
