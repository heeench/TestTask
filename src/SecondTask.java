import java.util.*;

public class SecondTask {

    static public class Profile {
        public Long id;
        public Long orgId;
        public Long groupId;

        public Profile(long id, long orgId, long groupId) {
            this.id = id;
            this.orgId = orgId;
            this.groupId = groupId;
        }

        @Override
        public String toString(){
            return "{" + id + ", " + orgId + ", " + groupId + "}" ;
        }
    }

    public Map<Long, Map<Long, List<Profile>>> groupByOrgIdAndGroupId(List<Profile> data) {
        Map<Long, Map<Long, List<Profile>>> sortedProfiles = new HashMap<>();

        for (Profile profile : data) {
            sortedProfiles
                    .computeIfAbsent(profile.orgId, orgId -> new HashMap<>())
                    .computeIfAbsent(profile.groupId, groupId -> new ArrayList<>())
                    .add(profile);
        }

        return sortedProfiles;
    }

    public static void main(String[] args) {
        SecondTask secondTask = new SecondTask();

        List<Profile> data = Arrays.asList(
                new Profile(1L, 0L,1L),
                new Profile(2L, 0L,1L),
                new Profile(3L, 0L,2L),
                new Profile(4L, 1L,1L),
                new Profile(5L, 1L,2L)
        );

        Map<Long, Map<Long, List<Profile>>> profiles = secondTask.groupByOrgIdAndGroupId(data);

       //Можно сделать через ObjectMapper, но к сожалению я решил без мавена тут все сделать и это было ошибкой...

        System.out.println(profiles);

        //Пример (входные данные):
        //        [1, 0, 1]
        //        [2, 0, 1]
        //        [3, 0, 2]
        //        [4, 1, 1]
        //        [5, 1, 2]
        //Result (вывод структуры):
        //     {
        //        "0": {
        //          "1" : [{1..}, {2..}],
        //          "2" : [{3..}],
        //        }
        //        "1": {
        //          "1" : [{4..}],
        //          "2" : [{5..}]
        //        }
        //     }
    }
}
