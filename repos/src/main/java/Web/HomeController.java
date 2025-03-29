package Web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookclub.model.Book;
import com.bookclub.service.impl.MemBookDao;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHome() {
        return "index";
    }

    @RequestMapping("/about")
    public String showAboutUs() {
        return "about";
    }

    @RequestMapping("/contact")
    public String showContactUs() {
        return "contact";
    }

    @GetMapping("/")
    public String showHome(Model model) {
        MemBookDao bookDao = new MemBookDao();
        List<Book> books = bookDao.list();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/{id}")
    public String getMonthlyBook(@PathVariable String id, Model model) {
        MemBookDao bookDao = new MemBookDao();
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "monthly-books/view";
    }

}
