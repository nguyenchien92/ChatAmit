package com.example.nguyen.chatamit.adapteres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.fragments.ContactFragment;
import com.example.nguyen.chatamit.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    public static final int TYPE_MESS = 1;
    public static final int TYPE_CONTACT = 0;
    public static final int TYPE_MORE = 2;

    private Context context;
    private List<User> userList;
    private View rootView;

    private ClickItemListener clickItemListener;
    private FilterAdapter mFilter = new FilterAdapter();
    private List<User> filteredData;


    private FragmentManager mn;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public CustomAdapter(Context context, List<User> userList, FragmentManager mn) {
        this.mn = mn;
        this.context = context;
        this.userList = userList;
    }

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_MESS:
                rootView = getView(context, R.layout.item_mess_layout, parent, false);
                return new MessHolder(rootView);
            case TYPE_CONTACT:
                rootView = getView(context, R.layout.item_contact_layout, parent, false);
                return new ContactHolder(rootView);
            case TYPE_MORE:
                rootView = getView(context, R.layout.item_more_layout, parent, false);
                return new MoreHolder(rootView);
            default:
                return null;
        }
    }

    public View getView(Context context, int layoutId, ViewGroup parent, boolean trueOrFalse) {
        return LayoutInflater.from(context).inflate(layoutId, parent, trueOrFalse);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        User user = userList.get(position);


        if (user != null) {
            switch (getItemViewType(position)) {
                case TYPE_MESS:
                    ((MessHolder) holder).setMessView(user);
                    ((MessHolder) holder).layoutItemMess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickItemListener.clickItem(position);
                        }
                    });

                    break;
                case TYPE_CONTACT:
                    ((ContactHolder) holder).setContactView(user);
                    ((ContactHolder) holder).layoutItemContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickItemListener.clickItem(position);
                        }
                    });

                    Fragment current = mn.findFragmentByTag(ContactFragment.class.getSimpleName());

                    if (!(current instanceof ContactFragment)) {
//                        ((ContactHolder) holder).stateRadioButton();
                        ((ContactHolder) holder).radioButton.setVisibility(View.VISIBLE);

                    }


                    break;
                case TYPE_MORE:
                    ((MoreHolder) holder).setMoreView(user);
                    ((MoreHolder) holder).layoutItemMore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickItemListener.clickItem(position);
                        }
                    });

                    break;
            }
        }
    }



    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class MessHolder extends RecyclerView.ViewHolder {

        private ImageView ivUser;
        private TextView tvName, tvDescription, tvTimeStamp;
        private LinearLayout layoutItemMess;

        public MessHolder(@NonNull View itemView) {
            super(itemView);

            ivUser = itemView.findViewById(R.id.iv_icon_user_mess);
            tvName = itemView.findViewById(R.id.tv_user_name_mess);
            tvDescription = itemView.findViewById(R.id.tv_user_description_mess);
            tvTimeStamp = itemView.findViewById(R.id.tv_time_mess);
            layoutItemMess = itemView.findViewById(R.id.linear_item_frag_mess);
        }

        public void setMessView(User user) {
            ivUser.setImageResource(user.getImageUser());
            tvName.setText(user.getName());
            tvDescription.setText(user.getDescription());
            tvTimeStamp.setText(user.getTime());
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        private TextView tvIcon, tvName;
        private LinearLayout layoutItemContact;
        private RadioButton radioButton;
        private CardView cardView;
        private LinearLayout linearContact;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);

            tvIcon = itemView.findViewById(R.id.tv_icon_contact);
            tvName = itemView.findViewById(R.id.tv_user_name_contact);
            layoutItemContact = itemView.findViewById(R.id.linear_item_contact);
            radioButton = itemView.findViewById(R.id.radio_button);
            cardView = itemView.findViewById(R.id.card_view);
            linearContact = itemView.findViewById(R.id.linear_item_contact);
        }

        public void setContactView(User user) {
            tvName.setText(user.getName());
            tvIcon.setText(user.getName().substring(0, 1));
            tvIcon.setTextSize(20);
            radioButton.setVisibility(View.GONE);
        }

        public void stateRadioButton() {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!radioButton.isSelected()) {
                        radioButton.setChecked(true);
                        radioButton.setSelected(true);
                    } else {
                        radioButton.setChecked(false);
                        radioButton.setSelected(false);
                    }
                }
            });
        }
    }


    public class MoreHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvMore;
        LinearLayout layoutItemMore;

        public MoreHolder(@NonNull View itemView) {
            super(itemView);

            tvMore = itemView.findViewById(R.id.tv_user_more);
            ivIcon = itemView.findViewById(R.id.iv_icon_user_more);
            layoutItemMore = itemView.findViewById(R.id.linear_item_more);
        }

        public void setMoreView(User user) {
            ivIcon.setImageResource(user.getImageUser());
            tvMore.setText(user.getDescription());
        }
    }


    @Override
    public int getItemViewType(int position) {
        User user = userList.get(position);

        switch (user.getDataType()) {
            case TYPE_MESS:
                return 1;
            case TYPE_CONTACT:
                return 0;
            case TYPE_MORE:
                return 2;
            default:
                return -1;
        }
    }

    public interface ClickItemListener {
        void clickItem(int position);
    }

    public class FilterAdapter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String filterString = charSequence.toString().toLowerCase();
            FilterResults results = new FilterResults();

            final List<User> mList = new ArrayList<>();
            User mUser;

            for (int i = 0; i < userList.size(); i++) {
                mUser = userList.get(i);
                if (mUser.getName().toLowerCase().contains(filterString)) {
                    mList.add(mUser);
                }
            }

            results.values = mList;
            results.count = mList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredData = (List<User>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
