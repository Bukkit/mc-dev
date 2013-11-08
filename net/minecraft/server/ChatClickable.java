package net.minecraft.server;

public class ChatClickable {

    private final EnumClickAction a;
    private final String b;

    public ChatClickable(EnumClickAction enumclickaction, String s) {
        this.a = enumclickaction;
        this.b = s;
    }

    public EnumClickAction a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            ChatClickable chatclickable = (ChatClickable) object;

            if (this.a != chatclickable.a) {
                return false;
            } else {
                if (this.b != null) {
                    if (!this.b.equals(chatclickable.b)) {
                        return false;
                    }
                } else if (chatclickable.b != null) {
                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "ClickEvent{action=" + this.a + ", value=\'" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int i = this.a.hashCode();

        i = 31 * i + (this.b != null ? this.b.hashCode() : 0);
        return i;
    }
}
