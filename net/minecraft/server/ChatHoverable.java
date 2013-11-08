package net.minecraft.server;

public class ChatHoverable {

    private final EnumHoverAction a;
    private final IChatBaseComponent b;

    public ChatHoverable(EnumHoverAction enumhoveraction, IChatBaseComponent ichatbasecomponent) {
        this.a = enumhoveraction;
        this.b = ichatbasecomponent;
    }

    public EnumHoverAction a() {
        return this.a;
    }

    public IChatBaseComponent b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            ChatHoverable chathoverable = (ChatHoverable) object;

            if (this.a != chathoverable.a) {
                return false;
            } else {
                if (this.b != null) {
                    if (!this.b.equals(chathoverable.b)) {
                        return false;
                    }
                } else if (chathoverable.b != null) {
                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "HoverEvent{action=" + this.a + ", value=\'" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int i = this.a.hashCode();

        i = 31 * i + (this.b != null ? this.b.hashCode() : 0);
        return i;
    }
}
