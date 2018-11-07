package javabase.thread;//package study.springboot.myspringboot;
//
//import com.google.protobuf.InvalidProtocolBufferException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import study.springboot.myspringboot.bean.Person;
//import study.springboot.myspringboot.model.protobuf.RoomStatusInfoLOuterClass;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ProtocolBufferTests {
//    @Test
//    public void protocSerandDec() {
//        RoomStatusInfoLOuterClass.RoomStatusInfoL.Builder data = RoomStatusInfoLOuterClass.RoomStatusInfoL.newBuilder();
//        RoomStatusInfoLOuterClass.RoomStatusInfoL.RoomStatusInfo.Builder room = RoomStatusInfoLOuterClass.RoomStatusInfoL.RoomStatusInfo.newBuilder();
//        room.setCheckIn("2018-09-10");
//        room.setHotelMainID("2132");
//        room.setCheckOut("2018-09-12");
//        room.setCustomerName("name");
//        room.setRoomNo("303");
//        room.setState(5);
//        data.addRoomStatusInfoList(room);
//
//
//        byte[] bytear = data.build().toByteArray();
//        try {
//            RoomStatusInfoLOuterClass.RoomStatusInfoL person2 = RoomStatusInfoLOuterClass.RoomStatusInfoL.parseFrom(bytear);
//            RoomStatusInfoLOuterClass.RoomStatusInfoL.RoomStatusInfo info = person2.getRoomStatusInfoList(0);
//            System.out.println(info.getCheckIn() + ", " + info.getCheckOut());
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
