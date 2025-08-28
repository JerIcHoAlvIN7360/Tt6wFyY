// 代码生成时间: 2025-08-28 22:56:08
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.util.Random;
import picocli.CommandLine;

/**
 * RandomNumberGenerator is a Quarkus application that generates a random number.
 * It uses the picocli library for command line argument parsing.
 */
@QuarkusMain
public class RandomNumberGenerator implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        // Parse command line arguments
        CommandLine cmd = new CommandLine(this);
        int exitCode = cmd.execute(args);
        return exitCode;
    }

    /**
     * Generates a random number.
     *
     * @param min The minimum value of the random number (inclusive).
     * @param max The maximum value of the random number (exclusive).
     *
     * @return A random number between min and max.
     */
    public int generateRandomNumber(int min, int max) {
        // Check for invalid range
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void main(String[] args) {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        // Default range is between 0 and 100
        int randomNumber = generator.generateRandomNumber(0, 100);
        System.out.println("Generated random number: " + randomNumber);
    }
}
