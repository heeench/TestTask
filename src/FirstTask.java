import java.util.concurrent.CompletableFuture;

public class FirstTask {

    public static class ProfileInfo {
        public UserInfo userInfo;
        public CompanyInfo companyInfo;

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public void setCompanyInfo(CompanyInfo companyInfo) {
            this.companyInfo = companyInfo;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public CompanyInfo getCompanyInfo() {
            return companyInfo;
        }
    }

    public static class UserInfo {
        public String name;
        public String age;

        public void setAge(String age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CompanyInfo {
        public String id;
        public String companyName;

        public void setId(String id) {
            this.id = id;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }

    // Метод выполняется за 1 секунду
    public ProfileInfo getProfileInfo(Long id) {
        CompletableFuture<UserInfo> userInfoFuture = CompletableFuture.supplyAsync(() -> getUserInfo(id));
        CompletableFuture<CompanyInfo> companyInfoFuture = CompletableFuture.supplyAsync(() -> getCompanyInfo(id));

        ProfileInfo profile = new ProfileInfo();
        profile.setUserInfo(userInfoFuture.join());
        profile.setCompanyInfo(companyInfoFuture.join());

        return profile;
    }

    private UserInfo getUserInfo(Long id) {
        try {
            Thread.sleep(1000); // симуляция вызова внешнего сервиса
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        UserInfo user = new UserInfo();
        user.setName("David");
        user.setAge("21");

        return user;
    }

    private CompanyInfo getCompanyInfo(Long id) {
        try {
            Thread.sleep(1000); // симуляция вызова внешнего сервиса
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        CompanyInfo company = new CompanyInfo();
        company.setId("1");
        company.setCompanyName("LightHouse");

        return company;
    }

    public static void main(String[] args) {
        FirstTask service = new FirstTask();
        long startTime = System.currentTimeMillis();
        ProfileInfo profileInfo = service.getProfileInfo(1L);
        long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime) + " ms");

        System.out.println("Name: " + profileInfo.getUserInfo().name +
                        ",\nAge: " + profileInfo.getUserInfo().age +
                        ",\nCompany: " + profileInfo.getCompanyInfo().companyName + ".");
    }
}

