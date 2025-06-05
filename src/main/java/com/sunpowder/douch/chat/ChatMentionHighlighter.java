package com.sunpowder.douch.chat;

public class ChatMentionHighlighter {
    public String highlightMentions(String message, String username) {
        return message.replaceAll("@" + username, "<b>@" + username + "</b>");
    }
}
