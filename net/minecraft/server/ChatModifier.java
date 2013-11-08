package net.minecraft.server;

public class ChatModifier {

    private ChatModifier a;
    private EnumChatFormat b;
    private Boolean c;
    private Boolean d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private ChatClickable h;
    private ChatHoverable i;
    private static final ChatModifier j = new ChatStyleRoot();

    public ChatModifier() {}

    public EnumChatFormat a() {
        return this.b == null ? this.n().a() : this.b;
    }

    public boolean b() {
        return this.c == null ? this.n().b() : this.c.booleanValue();
    }

    public boolean c() {
        return this.d == null ? this.n().c() : this.d.booleanValue();
    }

    public boolean d() {
        return this.f == null ? this.n().d() : this.f.booleanValue();
    }

    public boolean e() {
        return this.e == null ? this.n().e() : this.e.booleanValue();
    }

    public boolean f() {
        return this.g == null ? this.n().f() : this.g.booleanValue();
    }

    public boolean g() {
        return this.c == null && this.d == null && this.f == null && this.e == null && this.g == null && this.b == null && this.h == null && this.i == null;
    }

    public ChatClickable h() {
        return this.h == null ? this.n().h() : this.h;
    }

    public ChatHoverable i() {
        return this.i == null ? this.n().i() : this.i;
    }

    public ChatModifier setColor(EnumChatFormat enumchatformat) {
        this.b = enumchatformat;
        return this;
    }

    public ChatModifier setBold(Boolean obool) {
        this.c = obool;
        return this;
    }

    public ChatModifier setItalic(Boolean obool) {
        this.d = obool;
        return this;
    }

    public ChatModifier setStrikethrough(Boolean obool) {
        this.f = obool;
        return this;
    }

    public ChatModifier setUnderline(Boolean obool) {
        this.e = obool;
        return this;
    }

    public ChatModifier setRandom(Boolean obool) {
        this.g = obool;
        return this;
    }

    public ChatModifier a(ChatClickable chatclickable) {
        this.h = chatclickable;
        return this;
    }

    public ChatModifier a(ChatHoverable chathoverable) {
        this.i = chathoverable;
        return this;
    }

    public ChatModifier a(ChatModifier chatmodifier) {
        this.a = chatmodifier;
        return this;
    }

    private ChatModifier n() {
        return this.a == null ? j : this.a;
    }

    public String toString() {
        return "Style{hasParent=" + (this.a != null) + ", color=" + this.b + ", bold=" + this.c + ", italic=" + this.d + ", underlined=" + this.e + ", obfuscated=" + this.g + ", clickEvent=" + this.h() + ", hoverEvent=" + this.i() + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatModifier)) {
            return false;
        } else {
            ChatModifier chatmodifier = (ChatModifier) object;
            boolean flag;

            if (this.b() == chatmodifier.b() && this.a() == chatmodifier.a() && this.c() == chatmodifier.c() && this.f() == chatmodifier.f() && this.d() == chatmodifier.d() && this.e() == chatmodifier.e()) {
                label56: {
                    if (this.h() != null) {
                        if (!this.h().equals(chatmodifier.h())) {
                            break label56;
                        }
                    } else if (chatmodifier.h() != null) {
                        break label56;
                    }

                    if (this.i() != null) {
                        if (!this.i().equals(chatmodifier.i())) {
                            break label56;
                        }
                    } else if (chatmodifier.i() != null) {
                        break label56;
                    }

                    flag = true;
                    return flag;
                }
            }

            flag = false;
            return flag;
        }
    }

    public int hashCode() {
        int i = this.b.hashCode();

        i = 31 * i + this.c.hashCode();
        i = 31 * i + this.d.hashCode();
        i = 31 * i + this.e.hashCode();
        i = 31 * i + this.f.hashCode();
        i = 31 * i + this.g.hashCode();
        i = 31 * i + this.h.hashCode();
        i = 31 * i + this.i.hashCode();
        return i;
    }

    public ChatModifier clone() {
        ChatModifier chatmodifier = new ChatModifier();

        chatmodifier.c = this.c;
        chatmodifier.d = this.d;
        chatmodifier.f = this.f;
        chatmodifier.e = this.e;
        chatmodifier.g = this.g;
        chatmodifier.b = this.b;
        chatmodifier.h = this.h;
        chatmodifier.i = this.i;
        chatmodifier.a = this.a;
        return chatmodifier;
    }

    public ChatModifier m() {
        ChatModifier chatmodifier = new ChatModifier();

        chatmodifier.setBold(Boolean.valueOf(this.b()));
        chatmodifier.setItalic(Boolean.valueOf(this.c()));
        chatmodifier.setStrikethrough(Boolean.valueOf(this.d()));
        chatmodifier.setUnderline(Boolean.valueOf(this.e()));
        chatmodifier.setRandom(Boolean.valueOf(this.f()));
        chatmodifier.setColor(this.a());
        chatmodifier.a(this.h());
        chatmodifier.a(this.i());
        return chatmodifier;
    }

    static Boolean a(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.c = obool;
    }

    static Boolean b(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.d = obool;
    }

    static Boolean c(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.e = obool;
    }

    static Boolean d(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.f = obool;
    }

    static Boolean e(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.g = obool;
    }

    static EnumChatFormat a(ChatModifier chatmodifier, EnumChatFormat enumchatformat) {
        return chatmodifier.b = enumchatformat;
    }

    static ChatClickable a(ChatModifier chatmodifier, ChatClickable chatclickable) {
        return chatmodifier.h = chatclickable;
    }

    static ChatHoverable a(ChatModifier chatmodifier, ChatHoverable chathoverable) {
        return chatmodifier.i = chathoverable;
    }

    static Boolean b(ChatModifier chatmodifier) {
        return chatmodifier.c;
    }

    static Boolean c(ChatModifier chatmodifier) {
        return chatmodifier.d;
    }

    static Boolean d(ChatModifier chatmodifier) {
        return chatmodifier.e;
    }

    static Boolean e(ChatModifier chatmodifier) {
        return chatmodifier.f;
    }

    static Boolean f(ChatModifier chatmodifier) {
        return chatmodifier.g;
    }

    static EnumChatFormat g(ChatModifier chatmodifier) {
        return chatmodifier.b;
    }

    static ChatClickable h(ChatModifier chatmodifier) {
        return chatmodifier.h;
    }

    static ChatHoverable i(ChatModifier chatmodifier) {
        return chatmodifier.i;
    }
}
