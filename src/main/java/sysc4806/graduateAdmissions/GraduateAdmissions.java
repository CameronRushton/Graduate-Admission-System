package sysc4806.graduateAdmissions;

import lombok.val;
import lombok.var;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import sysc4806.graduateAdmissions.model.*;
import sysc4806.graduateAdmissions.repositories.InterestRepository;

import java.util.HashSet;

@SpringBootApplication
public class GraduateAdmissions {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GraduateAdmissions.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public CommandLineRunner demo(InterestRepository repository) {
        return (args) -> {
            //set student privileges
            var studentRole = new Role();
            studentRole.setRoleName("STUDENT");
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.APPLICATION).operation(Operation.CREATE).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.APPLICATION).operation(Operation.UPDATE).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.APPLICATION).operation(Operation.READ).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.APPLICATION).operation(Operation.DELETE).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.CREATE).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.UPDATE).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.READ).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.DELETE).build());
            studentRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL).target(Target.TERM).operation(Operation.READ).build());

            //set prof privileges
            var professorRole = new Role();
            professorRole.setRoleName("PROFESSOR");
            professorRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.CREATE).build());
            professorRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.UPDATE).build());
            professorRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.READ).build());
            professorRole.addPrivilege(
                    Privilege.builder().owner(Owner.SELF).target(Target.INTEREST).operation(Operation.DELETE).build());
            professorRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL_STUDENTS).target(Target.APPLICATION).operation(Operation.READ).build());
            professorRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL).target(Target.TERM).operation(Operation.READ).build());

            //set admin privileges
            var adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL_STUDENTS).target(Target.APPLICATION).operation(Operation.READ).build());
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL_STUDENTS).target(Target.INTEREST).operation(Operation.READ).build());
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL_PROFS).target(Target.INTEREST).operation(Operation.READ).build());
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL).target(Target.TERM).operation(Operation.CREATE).build());
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL).target(Target.TERM).operation(Operation.UPDATE).build());
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL).target(Target.TERM).operation(Operation.READ).build());
            adminRole.addPrivilege(
                    Privilege.builder().owner(Owner.ALL).target(Target.TERM).operation(Operation.DELETE).build());

            //create a sample user for each role
            val sampleStudent = new User("Peter", "Parker", "GAS.student4806@gmail.com",
                    studentRole, new HashSet<>(), new HashSet<>());
            val sampleProf = new User("Bruce", "Wayne", "GAS.prof4806@gmail.com",
                    professorRole, new HashSet<>(), new HashSet<>());
            val sampleAdmin = new User("Clark", "Kent", "GAS.staff4806@gmail.com",
                    adminRole, new HashSet<>(), new HashSet<>());

            //save the users into repository
            //repository.save(sampleStudent);
            //repository.save(sampleProf);
            //repository.save(sampleAdmin);
        };
    }
}
