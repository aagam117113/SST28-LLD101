public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        Powerable pj = (Powerable) reg.getFirstByName("Projector");
        pj.powerOn();
        ((InputConnectable) reg.getFirstByName("Projector")).connectInput("HDMI-1");

        BrightnessControllable lights = (BrightnessControllable) reg.getFirstByName("LightsPanel");
        lights.setBrightness(60);

        TemperatureControllable ac = (TemperatureControllable) reg.getFirstByName("AirConditioner");
        ac.setTemperatureC(24);

        Scanner scan = (Scanner) reg.getFirstByName("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Powerable) reg.getFirstByName("Projector")).powerOff();
        ((Powerable) reg.getFirstByName("LightsPanel")).powerOff();
        ((Powerable) reg.getFirstByName("AirConditioner")).powerOff();
    }
}
