package kr.hs.s2104.mirimunse.recyclerviewarcodian;

import android.annotation.SuppressLint;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hs.s2104.mirimunse.R;
import kr.hs.s2104.mirimunse.recyclerviewarcodian.DataInfor;
import kr.hs.s2104.mirimunse.recyclerviewarcodian.OnViewHolderItemClickListener;
import kr.hs.s2104.mirimunse.recyclerviewarcodian.ViewHolderInfo;

public class RecyclerVierAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<DataInfor> listData = new ArrayList<>();

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infor_recyclerview, parent, false);
        return new ViewHolderInfo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ViewHolderInfo ViewHolderInfo = (ViewHolderInfo)holder;
        ViewHolderInfo.onBind(listData.get(position),position, selectedItems);
        ViewHolderInfo.setOnViewHolderItemClickListener(new OnViewHolderItemClickListener() {
            @Override
            public void onViewHolderItemClick() {
                if (selectedItems.get(position)) {
                    // 펼쳐진 Item을 클릭 시
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 Item의 클릭상태를 지움
                    selectedItems.delete(prePosition);
                    // 클릭한 Item의 position을 저장
                    selectedItems.put(position, true);
                }
                // 해당 포지션의 변화를 알림
                if (prePosition != -1) notifyItemChanged(prePosition);
                notifyItemChanged(position);
                // 클릭된 position 저장
                prePosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(DataInfor data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
}