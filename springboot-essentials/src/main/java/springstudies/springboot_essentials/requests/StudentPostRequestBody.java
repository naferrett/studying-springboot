package springstudies.springboot_essentials.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//dto class
public class StudentPostRequestBody {
    @NotEmpty(message = "The name of this student cannot be empty or null.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
