package com.thesis2020.monitoringapp;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ReportFragment;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import javax.annotation.Nullable;

public class IncidentReports extends AppCompatActivity {

    private static final String TAG = "IncidentsReports";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Fall Detection");


    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    StorageReference storageReference;
    Button IncidentsTotalBtn, PerMonthBtn,logIncidentsBtn;

    TextView txtVIP1,txtVIP2,txtVIP3,txtVIP4,textViewData;
    TextView txtMonth1, txtMonth2, txtMonth3, txtMonth4;
    TextView txtFall1,txtFall2,txtFall3,txtFall4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_reports);

        //FIREBASE FUNCTION
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        IncidentsTotalBtn = findViewById(R.id.IncidentsTotalBtn);
        PerMonthBtn = findViewById(R.id.perMonthBtn);
        logIncidentsBtn = findViewById(R.id.logIncidentsBtn);


        txtVIP1 = findViewById(R.id.txtvip1);
        txtMonth1 = findViewById(R.id.txtmonth1);
        txtFall1 = findViewById(R.id.txtfalls1);

        textViewData = findViewById(R.id.text_view_data);

        IncidentsTotalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Query totalFall = FirebaseFirestore.getInstance()
                //.collection("Fall Detection")
                //.orderBy("VIP",Query.Direction.ASCENDING);
                // .limit(50);
                //Total
                //inc
                //BIND QUERY TO RECYCLEVIEW
                //FirestoreRecyclerOptions<Records> options = new FirestoreRecyclerOptions.Builder<Records>()
                //.setQuery(totalFall, Records.class)
                //.build();
                //totalFall.addSnapshotListener(new EventListener<QuerySnapshot>() {
                //@Override
                //public void onEvent(@Nullable QuerySnapshot snapshot,
                //                  @Nullable FirebaseFirestoreException e) {
                //if (e != null) {
                // Handle error
                //...
                //  return;
                //}
                // Convert query snapshot to a list
                //List<Records> totalFallofVIP = snapshot.toObjects(Records.class);

                //DocumentReference docRef = fStore
                //      .collection("Fall Detection")
                //    .document("47M70DwF23cvvLRph1bS");
                // asynchronously retrieve the document
                //Records<DocumentSnapshot> totalFallofVIP = docRef.get();
                // ...
                // future.get() blocks on response

                //Task<DocumentSnapshot> document = docRef.get();
                //if (document.exists()) {
                //   System.out.println("Document data: " + document.getData());
                //} else {
                //   System.out.println("No such document!");
                //}
                //}
            }
            });


                logIncidentsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intentchangeVIP = new Intent(IncidentReports.this, LogReport.class);
                        startActivity(intentchangeVIP);


                    }

                });

                PerMonthBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Query perMonth = FirebaseFirestore.getInstance()
                                .collection("Fall Detection")
                                .orderBy("Month");

                        FirestoreRecyclerOptions<Records> options = new FirestoreRecyclerOptions.Builder<Records>()
                                .setQuery(perMonth, Records.class)
                                .build();


                    }
                });

            }//onCreate

    public void loadNotes(View v) {
        notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Records records = documentSnapshot.toObject(Records.class);
                            records.setDocumentId(documentSnapshot.getId());
                            String documentId = records.getDocumentId();
                            String VIP  = records.getVIP();
                            String Day = records.getDay();
                            String Month = records.getMonth();
                            data += "VIP: " + VIP
                                    + "\nMonth: " + Month + "\nDate: " + Day + "\n\n";
                        }
                        textViewData.setText(data);
                    }
                });
    }


}