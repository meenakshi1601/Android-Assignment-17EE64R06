package com.hani.contactsmap;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class AndroidTabAndListView extends TabActivity {
    private static final String CONTACTS = "All Contatcts";
    private static final String MAP = "Contacts Map";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.androidtabandlistview);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec inboxSpec = tabHost.newTabSpec(CONTACTS);
        inboxSpec.setIndicator(CONTACTS);
        Intent inboxIntent = new Intent(this, ActivityJSON.class);
        inboxSpec.setContent(inboxIntent);

        TabHost.TabSpec outboxSpec = tabHost.newTabSpec(MAP);
        outboxSpec.setIndicator(MAP);

        Intent outboxIntent = new Intent(this, MapsActivity.class);
        outboxSpec.setContent(outboxIntent);

        tabHost.addTab(inboxSpec);
        tabHost.addTab(outboxSpec);
    }
}
