// 代码生成时间: 2025-09-15 06:58:35
import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * A service class for validating form data using Java Bean Validation API.
 */
public class FormDataValidatorService {

    // Validator instance
    private final Validator validator;

    public FormDataValidatorService() {
        // Obtain a Validator instance from the ValidatorFactory
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Validates the given object using the Java Bean Validation API.
     *
     * @param object The object to be validated.
     * @param <T> The type of the object to be validated.
     * @return A set of constraint violations if any, otherwise an empty set.
     * @throws IllegalArgumentException If the object is null.
     */
    public <T> Set<ConstraintViolation<T>> validate(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to validate cannot be null");
        }
        // Perform validation
        return validator.validate(object);
    }

    // Example usage of the service
    public static void main(String[] args) {
        FormDataValidatorService validatorService = new FormDataValidatorService();
        // Example object to validate, replace with your actual object
        MyFormData formData = new MyFormData();
        formData.setEmail("example@domain.com");
        formData.setUsername("username");

        // Validate the form data
        Set<ConstraintViolation<MyFormData>> violations = validatorService.validate(formData);

        if (!violations.isEmpty()) {
            System.out.println("Validation errors found: ");
            for (ConstraintViolation<MyFormData> violation : violations) {
                System.out.println("Property: " + violation.getPropertyPath() + ", Error: " + violation.getMessage());
            }
        } else {
            System.out.println("Form data is valid!");
        }
    }
}

/**
 * Example form data class with validation constraints.
 */
@RegisterForReflection
public class MyFormData {
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
