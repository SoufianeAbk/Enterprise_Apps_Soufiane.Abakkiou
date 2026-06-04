package be.ehb.enterpriseapp.controller;

import be.ehb.enterpriseapp.service.MailService;
import be.ehb.enterpriseapp.web.ContactForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final MailService mailService;

    public ContactController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public String form(Model model) {
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contact";
    }

    @PostMapping
    public String submit(@Valid ContactForm contactForm, BindingResult result,
                        Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "contact";
        }
        try {
            mailService.sendContactMessage(contactForm);
            redirectAttributes.addFlashAttribute("success",
                    "Bedankt voor je bericht! We nemen zo snel mogelijk contact met je op.");
            return "redirect:/contact";
        } catch (Exception ex) {
            model.addAttribute("error",
                    "Het bericht kon niet verzonden worden. Controleer de mailconfiguratie en probeer opnieuw.");
            return "contact";
        }
    }
}
