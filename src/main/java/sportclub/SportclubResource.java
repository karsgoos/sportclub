package sportclub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/sportclub")
public class SportclubResource {

    @GetMapping
    public List<String> getAlleSportClubs() {
        return Arrays.asList(
                "Sportclub A",
                "Sportclub B",
                "Sportclub C",
                "Sportclub D",
                "Sportclub E"
        );
    }
}
