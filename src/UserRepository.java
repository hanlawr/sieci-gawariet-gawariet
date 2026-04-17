import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mindrot.jbcrypt.BCrypt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String FILE_PATH = "users.json";
    private ObjectMapper mapper = new ObjectMapper();

    public void saveUsers(List<User> users) throws Exception {
        mapper.writerWithDefaultPrettyPrinter()
              .writeValue(new File(FILE_PATH), users);
    }

    public List<User> loadUsers() throws Exception {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        return mapper.readValue(file, new TypeReference<List<User>>() {});
    }

    // example hashing method
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}

