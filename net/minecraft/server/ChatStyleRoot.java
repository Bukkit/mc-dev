package net.minecraft.server;

final class ChatStyleRoot extends ChatModifier {

    ChatStyleRoot() {}

    public EnumChatFormat a() {
        return null;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public ChatClickable h() {
        return null;
    }

    public ChatHoverable i() {
        return null;
    }

    public ChatModifier setColor(EnumChatFormat enumchatformat) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setBold(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setItalic(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setStrikethrough(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setUnderline(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setRandom(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier a(ChatClickable chatclickable) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier a(ChatHoverable chathoverable) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier a(ChatModifier chatmodifier) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "Style.ROOT";
    }

    public ChatModifier clone() {
        return this;
    }

    public ChatModifier m() {
        return this;
    }
}
