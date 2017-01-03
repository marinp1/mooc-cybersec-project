package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignupController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String password, @RequestParam String address) {
        Account newAccount = new Account(name, password, address);
        accountRepository.save(newAccount);
        return "redirect:/profile?username=" + name;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String password) {
        Account tryAccount = accountRepository.findByUsername(name);

        if (tryAccount != null && tryAccount.getPassword().equals(password)) {
            return "redirect:/profile?username=" + name;
        } else {
            return "form";
        }
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String getUserList(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "userlist";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(HttpServletRequest request, Model model) {

        Account account = accountRepository.findByUsername(request.getParameter("username"));

        if (account == null) {
            return "form";
        } else {
            model.addAttribute("username", account.getUsername());
            model.addAttribute("address", account.getAddress());
            return "profile";
        }
    }

}
